package com.silveryark.rpc.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCResponse;

public class AuthorizeResponse extends RPCResponse<Object> {

    private String uid;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AuthorizeResponse(@JsonProperty("requestId") String requestId, @JsonProperty("status") STATUS status,
                             @JsonProperty("uid") String uid,
                             @JsonProperty("payload") Object payload) {
        super(requestId, status, payload);
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
