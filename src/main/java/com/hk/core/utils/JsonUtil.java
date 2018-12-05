package com.hk.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * User: hk
 * Date: 2017/8/10 下午4:31
 * version: 1.0
 */
public final class JsonUtil {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features =
        {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
        };

    /**
     * 对象转换为JSON
     * <p>show 方法的详细说明第一行<br>
     * <pre>
     * 例如：
     * {
     *   list字段如果为null，输出为[]，而不是null
     *   数值字段如果为null，输出为0，而不是null
     *   Boolean字段如果为null，输出为false，而不是null
     *   字符类型字段如果为null，输出为""，而不是null
     * }
     * </pre>
     *
     * @param object 对象
     * @return 字符串
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 对象装换为JSON
     *
     * @param object 对象
     * @return 字符串
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * json对象转为Bean
     *
     * @param text  json字符串
     * @param clazz bean对象
     * @return bean
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * json转为List对象
     *
     * @param text  json字符串
     * @param clazz 转换类型
     * @return
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSONArray.parseArray(text, clazz);
    }



}
