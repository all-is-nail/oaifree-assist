package org.example.oaifreeassist.service.impl;

import org.example.oaifreeassist.service.AccessTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccessTokenServiceImplTest {

    @Autowired
    private AccessTokenService accessTokenService;

    @Test
    void getAccessTokenByRefreshToken() {
        accessTokenService.getAccessTokenByRefreshToken("z6tRQ_1EdETDBeJ_BKWAmzIjUgAhL1ROQ86w6-iKwT_VW");
    }
}