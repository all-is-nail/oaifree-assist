package org.example.oaifreeassist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.oaifreeassist.entity.OaiSharedManagement;

/**
 * @author m0v1
 * @description 针对表【oai_shared_management(共享账号管理)】的数据库操作Service
 * @createDate 2024-06-24 13:00:16
 */
public interface OaiSharedManagementService extends IService<OaiSharedManagement> {

    void renewoaisharedmanagement(Long sharedTokenId, String accessToken);
}
