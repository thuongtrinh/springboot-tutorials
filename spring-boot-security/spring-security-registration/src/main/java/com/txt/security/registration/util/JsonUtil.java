package com.txt.security.registration.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JsonUtil {

    private static final Map<String, ObjectMapper> MAP_OBJECT_MAPPER = new HashMap<>();

    public static <T> T parseJson(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = getObjectMapper(clazz.getName());
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException ex) {
            log.error("Error when parse json string {} to class {}, exception {}", json, ex, clazz.getName());
            return null;
        }
    }

    public static String objToString(Object object) {
        try {
            ObjectMapper objectMapper = getObjectMapper(object.getClass().getName());
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error("Cannot write object {} as string, exception {}", object, ex.getMessage());
            return null;
        }

    }

    public static ObjectMapper getObjectMapper(String clazzName) {
        ObjectMapper om = MAP_OBJECT_MAPPER.get(clazzName);
        if (om == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
            MAP_OBJECT_MAPPER.put(clazzName, mapper);
            return mapper;
        }
        return om;
    }

    public static <T> T convertGenericValue(Object data, Class<T> clazz) {
        ObjectMapper objectMapper = getObjectMapper(clazz.getName());
        return objectMapper.convertValue(data, clazz);
    }
}
