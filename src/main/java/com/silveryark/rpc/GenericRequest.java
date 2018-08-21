package com.silveryark.rpc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericRequest extends RPCRequest<Object> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GenericRequest(@JsonProperty("requestId") String requestId,
                          @JsonProperty("payload") Object payload){
        super(requestId, payload);
    }

    public GenericRequest(Object payload) {
        super(payload);
    }
}
