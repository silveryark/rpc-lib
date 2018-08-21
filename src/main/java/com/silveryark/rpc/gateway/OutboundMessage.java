package com.silveryark.rpc.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCRequest;

public class OutboundMessage<T> extends RPCRequest<T> {
    private String topic;

    public OutboundMessage(String topic, T payload) {
        super(payload);
        this.topic = topic;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    protected OutboundMessage(@JsonProperty("requestId") String requestId,@JsonProperty("topic") String topic,
                            @JsonProperty("payload") T payload){
        super(requestId, payload);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
