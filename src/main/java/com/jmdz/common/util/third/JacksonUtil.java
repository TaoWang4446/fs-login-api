package com.jmdz.common.util.third;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.jmdz.fushan.model.config.Constants;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * jackson工具类
 *
 * @author LiCongLu
 * @date 2019-12-13 16:02
 */
public final class JacksonUtil {
    /**
     * 日期格式化
     */
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static ObjectMapper instance;

    private JacksonUtil() {
    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            synchronized (JacksonUtil.class) {
                if (instance == null) {
                    instance = createNullValueMapper();
                    instance.setTimeZone(TimeZone.getTimeZone(Constants.GMT8));
                    instance.setDateFormat(new SimpleDateFormat(PATTERN));

                    // 转换为格式化的json
                    instance.enable(SerializationFeature.INDENT_OUTPUT);

                    // 如果json中有新增的字段并且是实体类类中不存在的，不报错
                    instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                }
            }
        }
        return instance;
    }

    public static ObjectMapper createNullValueMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
                                  SerializerProvider paramSerializerProvider) throws IOException {
                // 设置返回null转为 空字符串""
                paramJsonGenerator.writeString("");
            }
        });
        return objectMapper;
    }

    /**
     * 转化成json字符串
     */
    public static String obj2Json(Object obj) {
        return obj2Json(obj, "");
    }

    /**
     * 转化成json字符串
     */
    public static String obj2Json(Object obj, String defJson) {
        try {
            return getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return defJson;
        }
    }

    /**
     * 字符串转实体
     *
     * @param content
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Obj(String content, Class<T> clazz) {
        try {
            return getInstance().readValue(content, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}