package org.example.oaifreeassist.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.oaifreeassist.service.OaiSharedManagementService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SharedTokenManagement {

    private final OaiSharedManagementService oaiSharedManagementService;

    /**
     * 每天 23:23 对 shared token 执行续期操作
     */
    @Scheduled(cron = "23 23 * * *")
    public void renewSharedToken() {
        log.info("Renew shared token");

    }
}
