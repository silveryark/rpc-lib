package com.silveryark.rpc.quartz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCResponse;

public class QuartzResponse extends RPCResponse<String> {
    /**
     * @param requestId 请求id
     * @param status    通讯层状态，OK代表服务调用成功
     * @param payload   如果是throwable的话就是通讯层异常的响应，否则就是普通响应，也和status有关
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public QuartzResponse(@JsonProperty("requestId") String requestId, @JsonProperty("status") STATUS status,
                          @JsonProperty("payload") String payload, @JsonProperty("throwable") Throwable throwable) {
        super(requestId, status, payload == null ? throwable : payload);
    }
}
