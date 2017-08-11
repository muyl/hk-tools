package com.hk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * User: hk
 * Date: 2017/8/10 下午4:31
 * version: 1.0
 */
public class JsonUtil {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
        SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
        SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
        SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
        SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 对象转换为JSON
     * <pre>
     *      list字段如果为null，输出为[]，而不是null
     *      数值字段如果为null，输出为0，而不是null
     *      Boolean字段如果为null，输出为false，而不是null
     *      字符类型字段如果为null，输出为""，而不是null
     * </pre>
     * @param object    对象
     * @return  字符串
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 对象装换为JSON
     * @param object    对象
     * @return 字符串
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }



}
