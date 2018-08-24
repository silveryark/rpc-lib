package com.silveryark.rpc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    protected RPCRequest(@JsonProperty("requestId") String requestId, @JsonProperty("payload") T payload) {
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
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(requestId)
                .append(payload)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        RPCRequest<?> that = (RPCRequest<?>) o;

        return new EqualsBuilder()
                .append(requestId, that.requestId)
                .append(payload, that.payload)
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
