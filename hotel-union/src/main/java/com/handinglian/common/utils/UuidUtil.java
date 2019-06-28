package com.handinglian.common.utils;

import java.util.UUID;

public class UuidUtil {

    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }
}
