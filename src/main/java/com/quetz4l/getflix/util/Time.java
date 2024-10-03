package com.quetz4l.getflix.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    static final DateTimeFormatter TimeStampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getTimeStamp() {
        return LocalDateTime.now().format(TimeStampFormatter);
    }
}
