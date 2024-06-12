package org.example.oaifreeassist.util;


import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class UnixTimestampConverterTest {

    public static final long UNIX_TIMESTAMP = Instant.EPOCH.getEpochSecond();

    @Test
    public void testConvertToDate() {
        // 转换为 Date 对象
        Date date = UnixTimestampConverter.convertToDate(UNIX_TIMESTAMP);
        System.out.println("java.util.Date 日期时间: " + date);
    }

    @Test
    public void testConvertToLocalDateTime() {
        LocalDateTime localDateTime = UnixTimestampConverter.convertToLocalDateTime(UNIX_TIMESTAMP);
        System.out.println("java.time.LocalDateTime 日期时间: " + localDateTime);
    }
}