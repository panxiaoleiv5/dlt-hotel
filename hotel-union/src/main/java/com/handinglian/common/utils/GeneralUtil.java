package com.handinglian.common.utils;

import com.handinglian.common.constants.Constants;
import com.handinglian.common.exception.BizException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtil {
    /**
     * 将原数据List与目标List进行比较，获取原数据缺失的部分
     *
     * @param parameterList 传参List
     * @param databaseList   数据库List
     * @return java.util.List<T>
     * @author pxl
     * @date 2019/6/5 14:55
     */
    public static <T> List<T> getMissingList(List<T> parameterList, List<T> databaseList) {
        List missingList = new ArrayList();
        for (T databaseId : databaseList) {
            if (!parameterList.contains(databaseId)) {
                missingList.add(databaseId);
            }
        }
        return missingList;
    }

    /**
     * 将原数据List与目标List进行比较，获取原数据增加的部分
     *
     * @param parameterList 传参List
     * @param databaseList   数据库List
     * @return java.util.List<T>
     * @author pxl
     * @date 2019/6/5 14:55
     */
    public static <T> List<T> getAddList(List<T> parameterList, List<T> databaseList) {
        List addList = new ArrayList();
        for (T parameterId : parameterList) {
            if (!databaseList.contains(parameterId)) {
                addList.add(parameterId);
            }
        }
        return addList;
    }

    /**
    * 对未加 : 的macIp每两位加一个 :
    * @author pxl
    * @param macIp
    * @return java.lang.String
    * @date 2019/6/5 15:20
    */
    public static String processMacIp(String macIp) {
        if (macIp.indexOf(":") == -1){
            boolean flag = macIp.length() % 2 == 1 ? true : false;
            if (flag){
                throw new BizException(Constants.ERROR, "macIp格式不正确，长度应为双数!");
            }
            StringBuffer stringBuffer = new StringBuffer(macIp);
            Integer index = 2;
            for (int i = 1; i < macIp.length()/2;i++) {
                stringBuffer.insert(index, ":");
                index = index + 3;
            }
            return stringBuffer.toString();
        } else {
            return macIp;
        }

    }

    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
