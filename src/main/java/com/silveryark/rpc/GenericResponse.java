package com.silveryark.rpc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse extends RPCResponse<Object> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GenericResponse(@JsonProperty("requestId") String requestId, @JsonProperty("status") STATUS status,
                           @JsonProperty("payload") Object payload) {
        super(requestId, status, payload);
    }

}
