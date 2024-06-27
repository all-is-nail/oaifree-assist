package org.example.oaifreeassist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.example.oaifreeassist.entity.OaiSharedManagement;
import org.example.oaifreeassist.mapper.OaiSharedManagementMapper;
import org.example.oaifreeassist.model.SharedTokenDTO;
import org.example.oaifreeassist.model.SharedTokenResponse;
import org.example.oaifreeassist.service.OaiSharedManagementService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author m0v1
 * @description 针对表【oai_shared_management(共享账号管理)】的数据库操作Service实现
 * @createDate 2024-06-24 13:00:16
 */
@RequiredArgsConstructor
@Service
public class OaiSharedManagementServiceImpl extends ServiceImpl<OaiSharedManagementMapper, OaiSharedManagement>
        implements OaiSharedManagementService {

    private final RestTemplate restTemplate;

    public void renewoaisharedmanagement(Long sharedTokenId, String accessToken) {
        LambdaQueryWrapper<OaiSharedManagement> sharedTokenQueryWrapper = Wrappers.lambdaQuery(OaiSharedManagement.class);

        SharedTokenDTO sharedTokenDTO = new SharedTokenDTO();
        String url = "https://chat.oaifree.com/token/register";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-Requested-With", "XMLHttpRequest");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("unique_name", sharedTokenDTO.getUnique_name());
        requestBody.add("access_token", sharedTokenDTO.getAccess_token());
        requestBody.add("expires_in", String.valueOf(sharedTokenDTO.getExpires_in()));
        requestBody.add("site_limit", sharedTokenDTO.getSite_limit());
        requestBody.add("gpt35_limit", String.valueOf(sharedTokenDTO.getGpt35_limit()));
        requestBody.add("gpt4_limit", String.valueOf(sharedTokenDTO.getGpt4_limit()));
        requestBody.add("show_conversations", String.valueOf(sharedTokenDTO.getShow_conversations()));
        requestBody.add("temporary_chat", String.valueOf(sharedTokenDTO.getTemporary_chat()));
        requestBody.add("reset_limit", String.valueOf(sharedTokenDTO.getReset_limit()));


        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<SharedTokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SharedTokenResponse.class);

        SharedTokenResponse responseBody = response.getBody();
        if (responseBody == null) {
            return;
        }

        // 更新 st

    }
}




