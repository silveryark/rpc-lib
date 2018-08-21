package com.silveryark.rpc;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public enum STATUS {
        OK(200), SERVER_ERROR(500), CLIENT_ERROR(400);

        private int status;

        STATUS(int status) {
            this.status = status;
        }

        public int intValue() {
            return status;
        }

    }
}
