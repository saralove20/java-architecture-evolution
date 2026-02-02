package com.be24.api.util;

import com.be24.api.common.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // JSON(요청 data) -> 객체 : 역직렬화 deserialization
    public static <T> T from(HttpServletRequest req, Class<T> clazz) {
        try {
            return objectMapper.readValue(req.getReader(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 객체 -> JSON(응답) : 직렬화 serialization
    public static String from(BaseResponse res) {
        try {
            return objectMapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
