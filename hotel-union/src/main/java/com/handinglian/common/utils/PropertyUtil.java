package com.handinglian.common.utils;

import java.util.ResourceBundle;

public class PropertyUtil {
    public static String getProperty(String filePath, String key){

        //test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可
        ResourceBundle resource = ResourceBundle.getBundle(filePath);
        String value = resource.getString(key);
        return value;
    }

    public static String getKkApi(String key){

        return getProperty("kk_request_api", key);
    }
}
