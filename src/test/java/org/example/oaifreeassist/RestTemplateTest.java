package org.example.oaifreeassist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.oaifreeassist.model.SharedTokenResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

    @Test
    public void testRestTemplate() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://chat.oaifree.com/token/register";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-Requested-With", "XMLHttpRequest");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("unique_name", "st-1718174067");
        body.add("access_token", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJzZXNzaW9uX2lkIjoiRjYyV21sdGpIUHoyLV84UG1STENrVV9UVjlYMlNIem0iLCJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiJoZWxsb3dvcmxkX2xhbmdfamF2YV9jQHByb3Rvbi5tZSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InBvaWQiOiJvcmctVGxUUmRISmNhYUN6dkpNMFRkRTFveldjIiwidXNlcl9pZCI6InVzZXItV1RURzFsbVRueHh5WW1XaWEyVUcxUUlvIn0sImlzcyI6Imh0dHBzOi8vYXV0aDAub3BlbmFpLmNvbS8iLCJzdWIiOiJhdXRoMHw2NThiMDliYzc2Y2UyNWI5NjJmNDQyY2UiLCJhdWQiOlsiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS92MSIsImh0dHBzOi8vb3BlbmFpLm9wZW5haS5hdXRoMGFwcC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNzE3NDc5Mzk1LCJleHAiOjE3MTgzNDMzOTUsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgbW9kZWwucmVhZCBtb2RlbC5yZXF1ZXN0IG9yZ2FuaXphdGlvbi5yZWFkIG9mZmxpbmVfYWNjZXNzIiwiYXpwIjoicGRsTElYMlk3Mk1JbDJyaExoVEU5VlY5Yk45MDVrQmgifQ.jGHjCnyE2nwH3HxM1ZPzVrCQ4LVKf1hUExJovQR6XiLtaKT906HNp5s9pNAla5yKvPqAfL0zWlmZKlw5_g0tYuViubxe6xEbeNcwhR9V11ySOMfaSZRhglEhzT2E8fug_cOu361GaXIELVTp6DwLjvTjXTT6AN51IojV5-fwyKHRzZkhggPLy3sTR8oEDLewLBAF7gLZ9PMVFEgVZJS-JOo552u4N7Z5VXloMZdLkWMUtWpQAbnCLnOEzLrkSAxJiwpndgfvbXJkxWGd_hgpoDsPMxVpvT7VL2YHOMSuFDRxWpHhnxl1KYyHBay3gvpzWeXWZSKFTVe0W8M9u8nxUQ");
        body.add("expires_in", "10000");
        body.add("site_limit", "https://shared.oaifree.com");
        body.add("gpt35_limit", "30");
        body.add("gpt4_limit", "30");
        body.add("show_conversations", "true");
        body.add("temporary_chat", "true");
        body.add("reset_limit", "true");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<SharedTokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SharedTokenResponse.class);

        System.out.println(new ObjectMapper().writeValueAsString(response.getBody()));
    }
}