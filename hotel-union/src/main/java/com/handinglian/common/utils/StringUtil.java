package com.handinglian.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author pxl
 * @description
 * @date 2019/4/28 17:01
 */
public class StringUtil {
    private static final char[] system62chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private StringUtil() {
    }

    public static boolean isBlank(String source) {
        return source == null || source.trim().length() == 0;
    }

    public static boolean isNotBlank(String source) {
        return source != null && source.trim().length() >= 1;
    }

    public static String randomCodeChars(int len) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        for(int i = 0; i < len; ++i) {
            sb.append(system62chars[r.nextInt(system62chars.length)]);
        }

        return sb.toString();
    }

    public static String encode62System(long num) {
        StringBuilder sb = new StringBuilder();

        long n;
        for(n = Math.abs(num); n > 61L; n /= 62L) {
            sb.append(system62chars[(int)(n % 62L)]);
        }

        sb.append(system62chars[(int)n]);
        sb.reverse();
        return sb.toString();
    }

    public static String randomCode6Digits() {
        return String.valueOf((int)((Math.random() * 9.0D + 1.0D) * 100000.0D));
    }

    public static String randomCode4Digits() {
        return String.valueOf((int)((Math.random() * 9.0D + 1.0D) * 1000.0D));
    }

    public static String limit(String input, int len) {
        return input.length() > len ? input.substring(0, len) : input;
    }

    public static String getDomainOfUrl(String url) {
        if (url == null) {
            return "";
        } else {
            if (url.startsWith("http://")) {
                url = url.substring(7);
            } else if (url.startsWith("https://")) {
                url = url.substring(8);
            }

            int indexDoamin = url.indexOf(47);
            String domain = indexDoamin >= 1 ? url.substring(0, indexDoamin) : url;
            return domain;
        }
    }

    public static String getDomainWithFirstSegmentOfUrl(String url) {
        if (url == null) {
            return "";
        } else {
            String domain = getDomainOfUrl(url);
            int indexDomain = url.indexOf(domain);
            if (indexDomain >= 0) {
                String remainder = url.substring(indexDomain + domain.length());
                if (remainder.startsWith("/")) {
                    remainder = remainder.substring(1);
                }

                int indexSlash = remainder.indexOf(47);
                if (indexSlash >= 0) {
                    domain = domain + "/" + remainder.substring(0, indexSlash);
                } else {
                    int indexQuestion = remainder.indexOf(63);
                    if (indexQuestion >= 0) {
                        remainder = remainder.substring(0, indexQuestion);
                    }

                    int indexWell = remainder.indexOf(35);
                    if (indexWell >= 0) {
                        remainder = remainder.substring(0, indexWell);
                    }

                    if (isNotBlank(remainder)) {
                        domain = domain + "/" + remainder;
                    }
                }
            }

            return domain;
        }
    }

    public static String fillLeftZero(String number, int length) {
        int numberLength = number == null ? 0 : number.length();
        String result = number == null ? "" : number;
        int i = 0;

        for(int len = length - numberLength; i < len; ++i) {
            result = "0" + result;
        }

        return result;
    }

    public static String formatList(List<?> list) {
        if (list == null) {
            return null;
        } else if (list.size() == 0) {
            return "[]";
        } else {
            StringBuffer result = null;
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                Object each = var2.next();
                if (result == null) {
                    result = (new StringBuffer()).append("[ ").append(each);
                } else {
                    result.append(", ").append(each);
                }
            }

            result.append(" ]");
            return result.toString();
        }
    }

    public static String formatGbValue(String gbValue) {
        if (isBlank(gbValue)) {
            return "";
        } else {
            int index = gbValue.indexOf(45);
            String result = index >= 1 ? gbValue.substring(index + 1, gbValue.length()) : gbValue;
            return result;
        }
    }

    /**
    * 将字符串按照分隔符转换成相应类型的List
    * @author pxl
    * @param str 字符串
    * @param punctuations 分隔符
    * @return java.util.List<java.lang.Long>
    * @date 2019/6/5 15:44
    */
    public static List<Integer> stringToIntegerList(String str, String... punctuations){
        String punctuation = punctuations.length > 0?punctuations[0]:",";
        List<Integer> list = new ArrayList<>();
        String[] strArray = str.split(punctuation);
        for (String string : strArray){
            list.add(Integer.valueOf(string));
        }
        return list;
    }
}
