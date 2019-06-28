package com.handinglian.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: pxl
 * @create: 2019/6/4 16:27
 */
public class FastJsonUtil {

    /**
     * 将原始List的值置值到目标List中
     * @author pxl
     * @param originalList 原始List
     * @param clazz 目标List
     * @return java.util.List<V>
     * @date 2019/6/3 11:13
     */
    public static <K, V> List<V> ListToList(List<K> originalList, Class<V> clazz){
        String originalStr = JSON.toJSONString(originalList);
        return JSON.parseArray(originalStr, clazz);
    }

    /**
     * 将原始对象的值置值到目标对象中
     * @author pxl
     * @param original 原始对象
     * @param clazz 目标对象的类对象
     * @return T
     * @date 2019/6/3 11:11
     */
    public static <T> T ObjectToObject(Object original, Class<T> clazz){
        String originalStr = JSON.toJSONString(original);
        return JSON.parseObject(originalStr, clazz);
    }
}
