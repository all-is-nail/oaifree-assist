package org.example.oaifreeassist.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class UnixTimestampConverter {
    /**
     * 将 Unix 时间戳（秒）转换为 java.util.Date 对象
     *
     * @param unixTimestamp Unix 时间戳（以秒为单位）
     * @return 对应的 Date 对象
     */
    public static Date convertToDate(long unixTimestamp) {
        return Date.from(Instant.ofEpochSecond(unixTimestamp));
    }

    /**
     * 将 Unix 时间戳（秒）转换为 LocalDateTime 对象
     *
     * @param unixTimestamp Unix 时间戳（以秒为单位）
     * @return 对应的 LocalDateTime 对象
     */
    public static LocalDateTime convertToLocalDateTime(long unixTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp), ZoneId.systemDefault());
    }
}
