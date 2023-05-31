package com.txt.dbsecurity.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class JsonHelper {

    private static Logger log = LoggerFactory.getLogger(JsonHelper.class);

    public static JsonHelper getInstance() {
        return new JsonHelper();
    }

    private static final Map<String, ObjectMapper> MAP_OBJECT_MAPPER = new HashMap<String, ObjectMapper>();

    public ObjectMapper getObjectMapper(String clazzName) {
        ObjectMapper om = MAP_OBJECT_MAPPER.get(clazzName);
        if (om == null) {
            ObjectMapper mapper = new ObjectMapper();
            MAP_OBJECT_MAPPER.put(clazzName, mapper);
            return mapper;
        }
        return om;
    }

    public String objectToJson(Object obj) {
        if (obj == null) {
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

    public Object jsonToObject(String json, Class<?> clazz) {
        if (json == null) {
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

    public static <T> List<T> readList(String str, Class<T> type) {
        return readList(str, ArrayList.class, type);
    }

    public static <T> List<T> readList(String str, Class<? extends Collection> type, Class<T> elementType) {
        final ObjectMapper mapper = newMapper();
        try {
            return mapper.readValue(str, mapper.getTypeFactory().constructCollectionType(type, elementType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        return mapper;
    }
}
