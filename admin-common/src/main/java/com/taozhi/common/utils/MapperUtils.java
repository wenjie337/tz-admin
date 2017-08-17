package com.taozhi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MapperUtils {

    public static final JsonMapper NONNULL_MAPPER = new JsonMapper(Include.NON_NULL);
    public static final JsonMapper NORMAL_MAPPER = JsonMapper.nonDefaultMapper();
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String toDateString(Date date) {
        if (date == null) {
            return "";
        }
        return FORMAT_DATE.format(date);
    }

    public static String toDateTimeString(Date date) {
        if (date == null) {
            return "";
        }
        return FORMAT_DATE_TIME.format(date);
    }

    public static <T> void update(String jsonString, T obj) {
        NONNULL_MAPPER.update(jsonString, obj);
    }

    public static <T> T map(String jsonString, Class<T> clazz, boolean excludeNull) {
        if (excludeNull) {
            return NONNULL_MAPPER.fromJson(jsonString, clazz);
        } else {
            return NORMAL_MAPPER.fromJson(jsonString, clazz);
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        return NONNULL_MAPPER.toJson(obj);
    }

    public static <T> T map(String jsonString, Class<T> clazz) {
        return map(jsonString, clazz, true);
    }

    public static <T> List<T> mapList(String jsonString, Class<T> clazz, boolean excludeNull) {
        if (excludeNull) {
            return NONNULL_MAPPER.fromJson(jsonString, NONNULL_MAPPER.createCollectionType(List.class, clazz));
        } else {
            return NORMAL_MAPPER.fromJson(jsonString, NORMAL_MAPPER.createCollectionType(List.class, clazz));
        }
    }

    public static <T> List<T> mapList(String jsonString, Class<T> clazz) {
        return mapList(jsonString, clazz, true);
    }

    public static List<Long> longList(String jsonArray) {
        return mapList(jsonArray, Long.class);
    }

    public static String longList(List<Long> array) {
        return NONNULL_MAPPER.toJson(array);
    }

    public static List<String> stringList(String jsonArray) {
        return mapList(jsonArray, String.class);
    }

    public static String stringList(List<String> array) {
        return NONNULL_MAPPER.toJson(array);
    }

}
