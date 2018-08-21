package com.silveryark.rpc.authorize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCResponse;

public class AuthorizeResponse extends RPCResponse<Object> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AuthorizeResponse(@JsonProperty("requestId") String requestId,@JsonProperty("status") STATUS status,
            @JsonProperty("payload") Object payload) {
        super(requestId, status, payload);
    }

}
