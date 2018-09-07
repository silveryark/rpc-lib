package com.silveryark.rpc.quartz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCRequest;

import java.util.Map;

public class QuartzRequest extends RPCRequest<QuartzRequest.QuartzPayload> {

    public QuartzRequest(@JsonProperty("payload") QuartzPayload payload) {
        super(payload);
    }

    public static class QuartzPayload {
        private final Map<String, Object> data;
        private final long delayInSeconds;
        private final String service;

        @JsonCreator
        public QuartzPayload(@JsonProperty("data") Map<String, Object> data,
                             @JsonProperty("delayInSeconds") long delayInSeconds,
                             @JsonProperty("service") String service) {
            this.data = data;
            this.delayInSeconds = delayInSeconds;
            this.service = service;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public long getDelayInSeconds() {
            return delayInSeconds;
        }

        public String getService() {
            return service;
        }
    }
}
