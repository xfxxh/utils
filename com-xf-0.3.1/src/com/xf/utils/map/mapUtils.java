package com.xf.utils.map;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xf.utils.collections.CollectionsUtils;

public class mapUtils {

    /**
     * 过滤掉集合里的空 - map
     * 
     * @param list
     * @return
     */
    public static Map<String, Object> delNullMap(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> keys = mapGetKey(map);
        for (String l : keys) {
            if (map.get(l) != null && !"".equals(map.get(l))) {
                result.put(l, map.get(l));
            }
        }
        return result;
    }

    /**
     * map是否为null
     * 
     * @param map
     * @return
     */
    public static boolean mapIsEmpty(Map<?, ?> map) {
        if (map != null && !map.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 获取map中的所有键值
     * 
     * @param map
     * @return
     */
    public static List<String> mapGetKey(Map<?, ?> map) {
        List<String> list = null;

        if (mapIsEmpty(map)) {
            return list;
        }
        @SuppressWarnings("unchecked")
        Set<String> set = (Set<String>) map.keySet();
        list = new ArrayList<>(set);

        return list;
    }

    /**
     * 获取map中的所有值
     * 
     * @param map
     * @return
     */
    public static List<Object> mapGetValue(Map<?, ?> map) {
        List<String> keys = mapGetKey(map);

        if (CollectionsUtils.listIsEmpty(keys)) {
            return null;
        }

        List<Object> value = new ArrayList<Object>();
        for (String key : keys) {
            value.add(map.get(key));
        }

        return value;
    }

    /**
     * 将map转成对应的javaBean
     * 
     * @param map
     * @param clazz
     * @return
     */
    public static <T, K, V> T mapToBean(Map<K, V> map, Class<T> clazz) {
        T t = null; // 声明javaBean
        try {
            BeanInfo bean = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pro = bean.getPropertyDescriptors();

            t = clazz.newInstance(); // 实例化javaBean

            for (PropertyDescriptor p : pro) {
                String key = p.getName(); // 取出属性名
                if (!map.containsKey(key)) {
                    continue;
                }

                Object value = map.get(key);
                Method setter = p.getWriteMethod();
                setter.invoke(t, value);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 将javaBean 转成 Map
     * 
     * @param bean
     * @return
     */
    public static <T, K, V> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean == null) {
            return null;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor p : pro) {
                String key = p.getName();

                if (key.equals("class")) {
                    continue;
                }

                Method getter = p.getReadMethod();
                Object value = getter.invoke(bean);
                map.put(key, value);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 将javaBean 转成 Map 去除空数据
     * 
     * @param bean
     * @return
     */
    public static <T, K, V> Map<String, Object> beanToMapDelNull(T bean) {
        Map<String, Object> result = beanToMap(bean);
        if (mapIsEmpty(result)) {
            return null;
        } else {
            return delNullMap(result);
        }
    }

    /**
     * javaBean 转 javaBean
     * 
     * @param bean
     * @param clazz
     * @return
     */
    public static <T, K, V> T copyBean(T bean, Class<T> clazz) {
        Map<String, Object> map = beanToMap(bean);
        return mapToBean(map, clazz);
    }
}
