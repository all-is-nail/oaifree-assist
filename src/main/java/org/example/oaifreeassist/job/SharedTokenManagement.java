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
     * 每天 23:23 对 shared token 执行续期操作
     */
    @Scheduled(cron = "23 30 * * *")
    public void renewSharedToken() {
        log.info("Renew shared token");
        // TODO
        // 查询处于有效期的 st
        LambdaQueryWrapper<OaiSharedManagement> queryWrapper = Wrappers.lambdaQuery(OaiSharedManagement.class);
        queryWrapper.gt(OaiSharedManagement::getExpirationTime, LocalDateTime.now());
        long validOaiShareTokenCount = oaiSharedManagementService.count(queryWrapper);
        if (validOaiShareTokenCount < 1) {
            return;
        }

        oaiSharedManagementService.list(queryWrapper).forEach(oaiSharedManagement -> {
            Long tokenId = oaiSharedManagement.getTokenId();
            LambdaQueryWrapper<OaiTokenManagement> oaiTokenManagementLambdaQueryWrapper = Wrappers.lambdaQuery(OaiTokenManagement.class);
            oaiTokenManagementLambdaQueryWrapper.eq(OaiTokenManagement::getId, tokenId)
                    .gt(OaiTokenManagement::getExpireTime, LocalDateTime.now());
            // 判断对应的 ac 是否处于有效期
            long validOaiAccessTokenCount = oaiTokenManagementService.count(oaiTokenManagementLambdaQueryWrapper);
            // |__ if ac 有效，则执行 st 续期
            if (validOaiAccessTokenCount > 0) {

                // |__ not 查询 ac 对应的账户是否存在 rt
            } else {
                OaiTokenManagement oaiTokenManagement = oaiTokenManagementService.getById(tokenId);
                Long accountId = oaiTokenManagement.getAccountId();

                LambdaQueryWrapper<OaiTokenManagement> oaiTokenManagementWrapper = Wrappers.lambdaQuery(OaiTokenManagement.class);
                oaiTokenManagementWrapper.eq(OaiTokenManagement::getAccountId, accountId).eq(OaiTokenManagement::getTokenType, 1);
                List<OaiTokenManagement> oaiRefreshToken = oaiTokenManagementService.list(oaiTokenManagementWrapper);

                // |____ 存在
                // |______则根据 rt 获取新的 ac，更新当前 st 对应的 ac 并执行 st 续期
                if (CollectionUtils.isNotEmpty(oaiRefreshToken)) {
                    String refreshTokenValue = oaiRefreshToken.get(0).getTokenValue();
                    String newAccessToken = accessTokenService.getAccessTokenByRefreshToken(refreshTokenValue);

                }
                // |____ 不存在，不处理当前 st 的续期
            }
        });

    }
}
