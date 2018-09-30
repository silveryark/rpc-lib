package com.silveryark.rpc.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCRequest;

public class OutboundMessage<T> extends RPCRequest<T> {
    private String topic;
    private String uid;

    public OutboundMessage(String topic, String uid, T payload) {
        super(payload);
        this.uid = uid;
        this.topic = topic;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OutboundMessage(@JsonProperty("requestId") String requestId, @JsonProperty("topic") String topic,
                           @JsonProperty("uid") String uid,
                           @JsonProperty("payload") T payload) {
        super(requestId, payload);
        this.uid = uid;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getUid() {
        return uid;
    }
}
