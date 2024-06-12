package org.example.oaifreeassist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.oaifreeassist.model.SharedTokenDTO;
import org.example.oaifreeassist.model.SharedTokenResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;

@SpringBootTest
class OaiFreeAssistApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testRestTemplate() throws JsonProcessingException {
        String url = "https://chat.oaifree.com/token/register";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.set("X-Requested-With", "XMLHttpRequest");

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

        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("unique_name", sharedTokenDTO.getUnique_name());
        formParams.add("access_token", sharedTokenDTO.getAccess_token());
        formParams.add("expires_in", String.valueOf(sharedTokenDTO.getExpires_in()));
        formParams.add("site_limit", sharedTokenDTO.getSite_limit());
        formParams.add("gpt35_limit", String.valueOf(sharedTokenDTO.getGpt35_limit()));
        formParams.add("gpt4_limit", String.valueOf(sharedTokenDTO.getGpt4_limit()));
        formParams.add("show_conversations", String.valueOf(sharedTokenDTO.getShow_conversations()));
        formParams.add("temporary_chat", String.valueOf(sharedTokenDTO.getTemporary_chat()));
        formParams.add("reset_limit", String.valueOf(sharedTokenDTO.getReset_limit()));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formParams, headers);

        // 发送 POST 请求
        ResponseEntity<SharedTokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SharedTokenResponse.class);

        // 输出响应
        System.out.println(new ObjectMapper().writeValueAsString(response.getBody()));
    }

    @Test
    void testConvertTimeStamp2DataTime() {
        long timestamp = Instant.now().getEpochSecond();
        System.out.println("current timestamp is " + timestamp);
        // 将时间戳转换为 LocalDateTime
        ZoneId zone = ZoneId.systemDefault();
        System.out.println("current zone is " + zone);
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zone);
        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        // 打印结果
        System.out.println("从时间戳转换得到的日期时间: " + formattedDateTime);
    }

    @Test
    void testTimeApi() {

        // LocalDate
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate: " + localDate);

        // LocalTime
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime: " + localTime);

        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime: " + localDateTime);

        // ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime: " + zonedDateTime);

        // Instant
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);

        // 获取时间戳
        System.out.println("current timestamp: " + instant.getEpochSecond());

        // 格式化 LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        System.out.println("Formatted LocalDateTime: " + formattedDateTime);

        // 转换为 java.util.Date
        Date date = Date.from(instant);
        System.out.println("java.util.Date: " + date);

        // 字符串类型日期转换成 LocalDateTime
        String dateTimeStr1 = "2024-06-12T14:30:00";
        // 使用 LocalDateTime.parse 方法
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeStr1);
        // 打印结果
        System.out.println("转换后的 LocalDateTime: " + dateTime1);

        String dateTimeStr2 = "2024-06-12 14:30:00";
        // 定义自定义格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 使用 DateTimeFormatter 进行解析
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeStr2, dateTimeFormatter);
        // 打印结果
        System.out.println("转换后的 LocalDateTime: " + dateTime2);
    }
}
