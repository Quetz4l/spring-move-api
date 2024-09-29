package com.quetz4l.getflix.service;

import ch.qos.logback.core.util.StringUtil;

public class Text {
    public static String textFormater(String text) {
        return StringUtil.capitalizeFirstLetter(text.trim());
    }
}
