package com.silveryark.rpc.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCRequest;

public class InboundMessage extends RPCRequest<Object> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InboundMessage(@JsonProperty("requestId") String requestId,
                          @JsonProperty("payload") Object payload){
        super(requestId, payload);
    }
    public InboundMessage(Object payload) {
        super(payload);
    }
}
