package com.silveryark.rpc.serializer;

import java.io.IOException;

//序列化接口，具体的序列化对象可以再做接口继承，这样的好处是可以直接用AbstractGeneric的Stub
public interface Serializer<T> {
    byte[] serialize(T request) throws IOException;

    T deserialize(byte[] msg) throws IOException;
}
