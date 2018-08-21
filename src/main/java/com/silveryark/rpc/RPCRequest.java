package com.silveryark.rpc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.ReflectionUtils;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RPCRequest<T> {
    private String requestId;
    private T payload;

    public RPCRequest(T payload) {
        this.requestId = UUID.randomUUID().toString();
        this.payload = payload;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    protected RPCRequest(@JsonProperty("requestId")String requestId,@JsonProperty("payload") T payload){
        this.requestId = requestId;
        this.payload = payload;
    }

    public String getRequestId() {
        return requestId;
    }

    public T getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
