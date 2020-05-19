package com.test.sid.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

public class jsonHelper {
    public static <T> T getObjectFromJsonString(Map<String,Object> jsonString, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper.readValue((JsonParser) jsonString,clazz);
    }
}
