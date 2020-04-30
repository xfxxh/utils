/**
 * 
 * @author xf_Xxh
 *
 */
package com.xf.utils.String;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述 字符串处理
 * 
 * @author xf_Xxh
 *
 */
public class StringUtils {

    /**
     * 判断是否是空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是数字
     * 
     * @param list
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 首字符大写
     * 
     * @param str
     * @return
     */
    public static String firstToUppercase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        str = str.toLowerCase();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将对象格式化成指定格式的字符串
     * 
     * @param type
     * @param obj
     * @return
     */
    public static String strFormatType(String type, Object obj) {
        return new DecimalFormat(type).format(obj);
    }

    /**
     * 将时间格式化成指定的字符串
     * 
     * @param type
     * @param date
     * @return
     */
    public static String dataFormat(String type, Date date) {
        return new SimpleDateFormat(type).format(date);
    }
}
