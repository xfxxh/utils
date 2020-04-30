package com.xf.utils.database;

import com.xf.utils.String.StringUtils;

/**
 * 格式化数据库操作数据
 * 
 * @author xf_Xxh
 *
 */
public class formatLikeUtils {

    /**
     * 格式化模糊查询
     * 
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (!StringUtils.isEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 格式化模糊查询
     * 
     * @param str
     * @return
     */
    public static String formatLike2(String str) {
        if (!StringUtils.isEmpty(str)) {
            return "%" + str + "%";
        } else {
            return "%%";
        }
    }

    /**
     * 格式化模糊查询 - 左模糊查询
     * 
     * @param str
     * @return
     */
    public static String formatLikeLeft(String str) {
        if (!StringUtils.isEmpty(str)) {
            return "%" + str;
        } else {
            return null;
        }
    }

    /**
     * 格式化模糊查询 - 左模糊查询
     * 
     * @param str
     * @return
     */
    public static String formatLikeLeft2(String str) {
        if (!StringUtils.isEmpty(str)) {
            return "%" + str;
        } else {
            return "%";
        }
    }

    /**
     * 格式化模糊查询 - 右模糊查询
     * 
     * @param str
     * @return
     */
    public static String formatLikeRight(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str + "%";
        } else {
            return null;
        }
    }

    /**
     * 格式化模糊查询 - 右模糊查询
     * 
     * @param str
     * @return
     */
    public static String formatLikeRight2(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str + "%";
        } else {
            return "%";
        }
    }
}
