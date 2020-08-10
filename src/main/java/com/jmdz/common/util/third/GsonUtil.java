package com.jmdz.common.util.third;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class GsonUtil {
    private static Gson instance;

    private GsonUtil() {
    }

    public static Gson getInstance() {
        if (instance == null) {
            synchronized (GsonUtil.class) {
                if (instance == null) {
                    instance = new Gson();
                }
            }
        }
        return instance;
    }

    public static String toJson(Object src) {
        try {
            return getInstance().toJson(src);
        } catch (Exception ex) {
            return "";
        }
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return getInstance().fromJson(json, classOfT);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 转成list
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> classOfT) {
        try {
            return getInstance().fromJson(json,
                    new TypeToken<List<T>>() {}.getType());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 转成list中有map的
     *
     * @param json
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String json) {
        try {
            return getInstance().fromJson(json,
                    new TypeToken<List<Map<String, T>>>() {}.getType());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 转成map的
     *
     * @param json
     * @return
     */
    public static <T> Map<String, T> toMaps(String json) {
        try {
            return getInstance().fromJson(json,
                    new TypeToken<Map<String, T>>() {}.getType());
        } catch (Exception ex) {
            return null;
        }
    }
}