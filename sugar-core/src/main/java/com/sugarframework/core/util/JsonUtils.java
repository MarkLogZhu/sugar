package com.sugarframework.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * @author zhu
 * @description: Json 转换工具类
 * @date 2019-06-14 15:02
 */
public class JsonUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为 null，输出为[]，而不是 null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为 null，输出为0，而不是 null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为 null，输出为false，而不是 null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为 null，输出为""，而不是 null
    };

    /**
     * 将对象转为 JSON 字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 转换成字符串 ,带有过滤器
     *
     * @param object
     * @return
     */
    public static String toJsonWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 转成具体的泛型bean对象
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }


    /**
     * 转换为具体的泛型List
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }


}
