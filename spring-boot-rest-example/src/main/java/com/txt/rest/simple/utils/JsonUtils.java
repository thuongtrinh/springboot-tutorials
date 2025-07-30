package com.txt.rest.simple.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class JsonUtils {

    public static JsonUtils getInstance() {
        return new JsonUtils();
    }

    private static final Map<String, ObjectMapper> MAP_OBJECT_MAPPER = new HashMap<>();

    public static ObjectMapper getObjectMapper(String clazzName) {
        ObjectMapper om = MAP_OBJECT_MAPPER.get(clazzName);
        if (om == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.registerModule(new Jdk8Module());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            MAP_OBJECT_MAPPER.put(clazzName, mapper);
            return mapper;
        }
        return om;
    }

    public static String objectToJson(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        ObjectMapper mapper = getObjectMapper(obj.getClass().getName());
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static Object jsonToObject(String json, Class<?> clazz) {
        if (Objects.isNull(json)) {
            return null;
        }
        ObjectMapper mapper = getObjectMapper(clazz.getClass().getName());
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
