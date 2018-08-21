package com.silveryark.rpc.serializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Stub 实现，只需要传Clz进来就可以构建通用的JSON Mapper
 *
 * @param <T>
 */
public abstract class AbstractGenericSerializer<T> implements Serializer<T> {
    private Class<T> clz;
    private ObjectMapper jsonMapper;

    protected AbstractGenericSerializer(Class<T> clz) {
        this.clz = clz;
        jsonMapper = new ObjectMapper();
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public byte[] serialize(T request) throws IOException {
        return jsonMapper.writeValueAsBytes(request);
    }

    @Override
    public T deserialize(byte[] msg) throws IOException {
        return jsonMapper.readValue(msg, clz);
    }
}
