package me.jincrates.global.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    public static ZoneId defaultZoneId = ZoneId.of("Asia/Seoul");

    public static LocalDateTime getToday() {
        return LocalDateTime.now(defaultZoneId);
    }

    public static String convertFormat(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}
