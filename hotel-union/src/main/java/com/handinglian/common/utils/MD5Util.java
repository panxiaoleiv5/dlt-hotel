package com.handinglian.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String encrypt(String str) {
        String result = "";

        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            byte[] messageDigest = algorithm.digest(str.getBytes());
            result = byteToHexString(messageDigest);
        } catch (NoSuchAlgorithmException var4) {
            ;
        }

        return result;
    }

    private static String byteToHexString(byte[] textByte) {
        StringBuilder hexString = new StringBuilder(64);
        byte[] arr$ = textByte;
        int len$ = textByte.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte bt = arr$[i$];
            int byteValue = 255 & bt;
            if (byteValue < 16) {
                hexString.append("0").append(Integer.toHexString(255 & bt));
            } else {
                hexString.append(Integer.toHexString(255 & bt));
            }
        }

        return hexString.toString();
    }
}
