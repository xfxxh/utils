/**
 * @author xf_Xxh
 *
 */
package com.xf.utils.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生成随机数工具
 * 
 * @author xf_Xxh
 *
 */
public class RandUtils {

    // 数字字符串集合
    public static final String[] NUM_CASE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    // 小写英文字符集合
    public static final String[] LOW_CASE = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
    // 大写英文字符集合
    public static final String[] UPPER_CASE = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    // 特殊字符
    public static final String[] SPECIAL_CASE = { "~", "!", "@", "$", "#", "%", "^", "&", "*", "(", ")", "_", "+", ",",
            ".", "?", "/", "<", ">" };
    // 随机工具声明
    private static Random random = new Random();
    // 全数字集合
    private static List<String> numList = new ArrayList<String>();
    // 小写字符集合
    private static List<String> lowList = new ArrayList<String>();
    // 大写字母集合
    private static List<String> upperList = new ArrayList<String>();
    // 数字加小写字母
    private static List<String> numLowList = new ArrayList<String>();
    // 数字加大写字母
    private static List<String> numUpperList = new ArrayList<String>();
    // 大小写字母集合
    private static List<String> lowUpperList = new ArrayList<String>();
    // 大小写加数字集合
    private static List<String> allList = new ArrayList<String>();

    /**
     * 获取指定位数包含数字的随机数
     * 
     * @param len
     * @return
     */
    public static String randNumCase(Integer star, Integer end) {
        return randNumCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randNumCase(Integer len) {
        if (numList.isEmpty()) {
            loadNum(numList);
        }
        return randStr(len, numList);
    }

    /**
     * 获取指定位数包含小写字母的随机数
     * 
     * @param len
     * @return
     */
    public static String randLowCase(Integer star, Integer end) {
        return randLowCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randLowCase(Integer len) {
        if (lowList.isEmpty()) {
            loadLowCase(lowList);
        }
        return randStr(len, lowList);
    }

    /**
     * 获取指定位数包含大写字母的随机数
     * 
     * @param len
     * @return
     */
    public static String randUpperCase(Integer star, Integer end) {
        return randUpperCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randUpperCase(Integer len) {
        if (upperList.isEmpty()) {
            loadUpperCase(upperList);
        }
        return randStr(len, upperList);
    }

    /**
     * 获取指定位数包含数字和小写字母的随机数
     * 
     * @param len
     * @return
     */
    public static String randNumLowCase(Integer star, Integer end) {
        return randNumLowCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randNumLowCase(Integer len) {
        if (numLowList.isEmpty()) {
            loadNum(numLowList);
            loadLowCase(numLowList);
        }
        return randStr(len, numLowList);
    }

    /**
     * 获取指定位数包含数字和大写字母的随机数
     * 
     * @param len
     * @return
     */
    public static String randNumUpperCase(Integer star, Integer end) {
        return randNumUpperCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randNumUpperCase(Integer len) {
        if (numUpperList.isEmpty()) {
            loadNum(numUpperList);
            loadUpperCase(numUpperList);
        }
        return randStr(len, numUpperList);
    }

    /**
     * 获取指定位数包含大小写字母的随机数
     * 
     * @param len
     * @return
     */
    public static String randLowUpperCase(Integer star, Integer end) {
        return randLowUpperCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randLowUpperCase(Integer len) {
        if (lowUpperList.isEmpty()) {
            loadLowCase(lowUpperList);
            loadUpperCase(lowUpperList);
        }
        return randStr(len, lowUpperList);
    }

    /**
     * 获取指定位数包含大小写字母和数字的随机数
     * 
     * @param len
     * @return
     */
    public static String randLowUpperNum(Integer star, Integer end) {
        return randLowUpperNum(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randLowUpperNum(Integer len) {
        if (allList.isEmpty()) {
            loadNum(allList);
            loadLowCase(allList);
            loadUpperCase(allList);
        }
        return randStr(len, allList);
    }

    /**
     * 获取指定位数包含大小写字母、特殊字符和数字的随机数
     * 
     * @param len
     * @return
     */
    public static String randAllCase(Integer star, Integer end) {
        return randAllCase(random.nextInt(end) % (end - star + 1) + star);
    }

    public static String randAllCase(Integer len) {
        if (allList.isEmpty()) {
            loadNum(allList);
            loadLowCase(allList);
            loadUpperCase(allList);
            loadSpecial(allList);
        }
        return randStr(len, allList);
    }

    /**
     * 获取指定位数的随机数
     * 
     * @param len
     * @return
     */
    public static String randStr(Integer len, List<String> list) {

        String randStr = "";

        for (int i = 0; i < len; i++) {
            randStr += list.get(random.nextInt(list.size()));
        }

        return randStr;
    }

    /**
     * 加载数字的list集合
     */
    private static void loadNum(List<String> list) {
        for (String str : NUM_CASE) {
            list.add(str);
        }
    }

    /**
     * 加载小写字母的list集合
     */
    private static void loadLowCase(List<String> list) {
        for (String str : LOW_CASE) {
            list.add(str);
        }
    }

    /**
     * 加载大写字母的list集合
     */
    private static void loadUpperCase(List<String> list) {
        for (String str : UPPER_CASE) {
            list.add(str);
        }
    }

    /**
     * 加载大写字母的list集合
     */
    private static void loadSpecial(List<String> list) {
        for (String str : SPECIAL_CASE) {
            list.add(str);
        }
    }

}