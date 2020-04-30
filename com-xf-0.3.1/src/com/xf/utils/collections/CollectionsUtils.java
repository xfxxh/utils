package com.xf.utils.collections;

import java.util.ArrayList;
import java.util.List;

import com.xf.utils.String.StringUtils;

public class CollectionsUtils {

    /**
     * 过滤掉集合里的空 - list
     * 
     * @param list
     * @return
     */
    public static List<String> delNullList(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (!StringUtils.isEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }

    /**
     * list是否为空
     * 
     * @param list
     * @return
     */
    public static boolean listIsEmpty(List<?> list) {
        if (list != null && !list.isEmpty()) {
            return false;
        }
        return true;
    }

}
