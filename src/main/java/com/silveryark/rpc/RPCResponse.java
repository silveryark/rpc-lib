package com.silveryark.rpc;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class RPCResponse<T> {
    private String requestId;
    private STATUS status;
    private T payload;
    private Throwable throwable;

    /**
     * @param requestId 请求id
     * @param status    通讯层状态，OK代表服务调用成功
     * @param payload   如果是throwable的话就是通讯层异常的响应，否则就是普通响应，也和status有关
     */
    public RPCResponse(String requestId, STATUS status, T payload) {
        this.requestId = requestId;
        this.status = status;
        if (Throwable.class.isAssignableFrom(payload.getClass())) {
            this.throwable = (Throwable) payload;
        } else {
            this.payload = payload;
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public STATUS getStatus() {
        return status;
    }

    public T getPayload() {
        return payload;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(requestId)
                .append(status)
                .append(payload)
                .append(throwable)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        RPCResponse<?> that = (RPCResponse<?>) o;

        return new EqualsBuilder()
                .append(requestId, that.requestId)
                .append(status, that.status)
                .append(payload, that.payload)
                .append(throwable, that.throwable)
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public enum STATUS {
        OK(200), SERVER_ERROR(500), CLIENT_ERROR(400);

        private int code;

        STATUS(int code) {
            this.code = code;
        }

        public int intValue() {
            return code;
        }

    }
}
