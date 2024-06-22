package org.example.oaifreeassist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.oaifreeassist.entity.OaiAccount;
import org.example.oaifreeassist.entity.OaiTokenManagement;
import org.example.oaifreeassist.mapper.OaiAccountMapper;
import org.example.oaifreeassist.service.OaiAccountService;
import org.example.oaifreeassist.service.OaiTokenManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author m0v1
 * @description 针对表【oai_account(OAI 账号信息)】的数据库操作Service实现
 * @createDate 2024-06-15 19:16:27
 */
@Service
public class OaiAccountServiceImpl extends ServiceImpl<OaiAccountMapper, OaiAccount> implements OaiAccountService {

    @Autowired
    private OaiTokenManagementService oaiTokenManagementService;

    @Override
    public List<OaiTokenManagement> getTokensByAccountId(Long accountId) {
        LambdaQueryWrapper<OaiTokenManagement> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(OaiTokenManagement::getAccountId, accountId);
        return oaiTokenManagementService.list(queryWrapper);
    }
}




