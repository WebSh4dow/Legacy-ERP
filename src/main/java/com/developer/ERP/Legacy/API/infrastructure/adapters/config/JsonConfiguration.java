package com.developer.ERP.Legacy.API.infrastructure.adapters.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.Map;

public class JsonConfiguration {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new Jdk8Module());
    }

    public static String json(Object object) {
        try {
            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String,String> desserialize(String json) {
       try {
           return mapper.readValue(json, new TypeReference<Map<String, String>>() {});
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
    }
}
