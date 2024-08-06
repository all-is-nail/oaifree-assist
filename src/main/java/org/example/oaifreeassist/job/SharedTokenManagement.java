package org.example.oaifreeassist.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.oaifreeassist.entity.OaiSharedManagement;
import org.example.oaifreeassist.entity.OaiTokenManagement;
import org.example.oaifreeassist.service.AccessTokenService;
import org.example.oaifreeassist.service.OaiSharedManagementService;
import org.example.oaifreeassist.service.OaiTokenManagementService;
import org.example.oaifreeassist.service.impl.OaiAccountServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SharedTokenManagement {

    private final OaiSharedManagementService oaiSharedManagementService;
    private final OaiTokenManagementService oaiTokenManagementService;
    private final OaiAccountServiceImpl oaiAccountService;
    private final AccessTokenService accessTokenService;

    /**
     * 每天 23:30 对 shared token 执行续期操作
     */
    @Scheduled(cron = "0 30 23 * * *")
    public void renewSharedToken() {
        log.info("Renew shared token");
        // TODO
        // 查询处于有效期的 st（一天内到期）
        LambdaQueryWrapper<OaiSharedManagement> queryWrapper = Wrappers.lambdaQuery(OaiSharedManagement.class);
        LocalDateTime now = LocalDateTime.now();
        // 限制条件：一天内到期
        queryWrapper.gt(OaiSharedManagement::getExpirationTime, now)
                .lt(OaiSharedManagement::getExpirationTime, now.plusDays(1L));
        long validOaiShareTokenCount = oaiSharedManagementService.count(queryWrapper);
        if (validOaiShareTokenCount < 1) {
            log.warn("未找到将在一天内到期的st，本次续期操作执行结束！");
            return;
        }

        oaiSharedManagementService.list(queryWrapper).forEach(oaiSharedManagement -> {
            Long oaiSharedManagementId = oaiSharedManagement.getId();
            Long tokenId = oaiSharedManagement.getTokenId();

            // 判断对应的 ac 是否处于有效期
            OaiTokenManagement accessTokenInfo = oaiTokenManagementService.getById(tokenId);
            if (accessTokenInfo == null) {
                log.warn("当前st:{}未找到对应的ac，本次续期操作执行结束！", oaiSharedManagement.getShareToken());
                return;
            }
            // |__ if ac 有效，则执行 st 续期
            if (accessTokenInfo.getExpireTime().isAfter(now)) {
                oaiSharedManagementService.renewoaisharedmanagement(oaiSharedManagementId, accessTokenInfo.getTokenValue());
                // 更新续期后的 st
                oaiSharedManagementService.updateById(oaiSharedManagement);
                // |__ not 查询 ac 对应的账户是否存在 rt
            } else {
                LambdaQueryWrapper<OaiTokenManagement> oaiTokenManagementWrapper = Wrappers.lambdaQuery(OaiTokenManagement.class);
                oaiTokenManagementWrapper.eq(OaiTokenManagement::getAccountId, accessTokenInfo.getAccountId()).eq(OaiTokenManagement::getTokenType, 1);
                List<OaiTokenManagement> oaiRefreshToken = oaiTokenManagementService.list(oaiTokenManagementWrapper);

                // |____ 存在
                // |______则根据 rt 获取新的 ac，更新当前 st 对应的 ac 并执行 st 续期
                if (CollectionUtils.isNotEmpty(oaiRefreshToken)) {
                    OaiTokenManagement oaiTokenManagement = oaiRefreshToken.get(0);
                    String refreshTokenValue = oaiTokenManagement.getTokenValue();
                    String newAccessToken = accessTokenService.getAccessTokenByRefreshToken(refreshTokenValue);
                    // 更新 ac
                    oaiTokenManagement.setTokenValue(newAccessToken);
                    oaiTokenManagementService.updateById(oaiTokenManagement);
                    // 续期
                    oaiSharedManagementService.renewoaisharedmanagement(oaiSharedManagementId, newAccessToken);

                }
                // |____ 不存在，不处理当前 st 的续期
            }
        });

    }
}
