package com.quetz4l.getflix.util;

import com.quetz4l.getflix.exceptions.custom.NotBooleanType;

import java.util.Objects;

public class Bool {
    public static boolean parseBoolean(String string) throws NotBooleanType {
        boolean bool;

        if (Objects.equals(string, "true")) {
            bool = true;
        } else if (Objects.equals(string, "false")) {
            bool = false;
        } else {
            throw new NotBooleanType("Force must be true or false");
        }

        return bool;

    }
}
