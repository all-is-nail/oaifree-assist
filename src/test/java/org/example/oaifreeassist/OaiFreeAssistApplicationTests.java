package org.example.oaifreeassist;

import org.example.oaifreeassist.dto.SharedTokenDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootTest
class OaiFreeAssistApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void testRestTemplate(){
        String url = "https://chat.oaifree.com/token/register";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.set("X-Requested-With", "XMLHttpRequest");
        // headers.set("User-Agent", "Apifox/1.0.0 (https://apifox.com)");

        // 设置请求参数
        SharedTokenDTO sharedTokenDTO = new SharedTokenDTO();
        sharedTokenDTO.setUnique_name("st-1718174067");
        sharedTokenDTO.setAccess_token("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJzZXNzaW9uX2lkIjoiRjYyV21sdGpIUHoyLV84UG1STENrVV9UVjlYMlNIem0iLCJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiJoZWxsb3dvcmxkX2xhbmdfamF2YV9jQHByb3Rvbi5tZSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InBvaWQiOiJvcmctVGxUUmRISmNhYUN6dkpNMFRkRTFveldjIiwidXNlcl9pZCI6InVzZXItV1RURzFsbVRueHh5WW1XaWEyVUcxUUlvIn0sImlzcyI6Imh0dHBzOi8vYXV0aDAub3BlbmFpLmNvbS8iLCJzdWIiOiJhdXRoMHw2NThiMDliYzc2Y2UyNWI5NjJmNDQyY2UiLCJhdWQiOlsiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS92MSIsImh0dHBzOi8vb3BlbmFpLm9wZW5haS5hdXRoMGFwcC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNzE3NDc5Mzk1LCJleHAiOjE3MTgzNDMzOTUsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgbW9kZWwucmVhZCBtb2RlbC5yZXF1ZXN0IG9yZ2FuaXphdGlvbi5yZWFkIG9mZmxpbmVfYWNjZXNzIiwiYXpwIjoicGRsTElYMlk3Mk1JbDJyaExoVEU5VlY5Yk45MDVrQmgifQ.jGHjCnyE2nwH3HxM1ZPzVrCQ4LVKf1hUExJovQR6XiLtaKT906HNp5s9pNAla5yKvPqAfL0zWlmZKlw5_g0tYuViubxe6xEbeNcwhR9V11ySOMfaSZRhglEhzT2E8fug_cOu361GaXIELVTp6DwLjvTjXTT6AN51IojV5-fwyKHRzZkhggPLy3sTR8oEDLewLBAF7gLZ9PMVFEgVZJS-JOo552u4N7Z5VXloMZdLkWMUtWpQAbnCLnOEzLrkSAxJiwpndgfvbXJkxWGd_hgpoDsPMxVpvT7VL2YHOMSuFDRxWpHhnxl1KYyHBay3gvpzWeXWZSKFTVe0W8M9u8nxUQ");
        sharedTokenDTO.setExpires_in(10000);
        sharedTokenDTO.setSite_limit("https://shared.oaifree.com");
        sharedTokenDTO.setGpt35_limit(30);
        sharedTokenDTO.setGpt4_limit(10);
        sharedTokenDTO.setShow_conversations(false);
        sharedTokenDTO.setTemporary_chat(false);
        sharedTokenDTO.setReset_limit(false);

        HttpEntity<SharedTokenDTO> requestEntity = new HttpEntity<>(sharedTokenDTO, headers);
        // 发送 POST 请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // 输出响应
        System.out.println(response.getBody());
    }
}
