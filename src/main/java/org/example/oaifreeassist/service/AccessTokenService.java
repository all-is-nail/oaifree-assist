package org.example.oaifreeassist.service;


public interface AccessTokenService {

    /**
     * 通过 refresh token 获取 access token
     *
     * @param refreshToken
     * @return
     */
    String getAccessTokenByRefreshToken(String refreshToken);

    /**
     * 根据主键更新access token
     *
     * @param accessTokenId
     */
    void updateAccessToken(Long accessTokenId);
}
