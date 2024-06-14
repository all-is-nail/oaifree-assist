package org.example.oaifreeassist.service.impl;

import org.example.oaifreeassist.model.gen.ac.GetAccessTokenResponse;
import org.example.oaifreeassist.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAccessTokenByRefreshToken(String refreshToken) {
        String url = "https://token.oaifree.com/api/auth/refresh";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-Requested-With", "XMLHttpRequest");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GetAccessTokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, GetAccessTokenResponse.class);
        GetAccessTokenResponse responseBody = response.getBody();
        if (responseBody == null) {
            return "";
        }
        String accessToken = responseBody.getAccessToken();
        // 时间戳
        Long expiresIn = responseBody.getExpiresIn();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(expiresIn);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("generated access token is:" + accessToken + "\n到期时间为:" + expirationDateTime.format(formatter));
        return accessToken;
    }

    @Override
    public void updateAccessToken(Long accessTokenId) {

    }
}
