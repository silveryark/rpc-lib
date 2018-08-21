package com.silveryark.rpc;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class Brokers {

    private WebClient.Builder clientBuilder = WebClient.builder();

    public Builder create(String baseUrl) {
        return new Builder(clientBuilder.baseUrl(baseUrl).build());
    }

    public static class Builder {

        private WebClient client;
        private Object body;
        private String uri = "/message";

        public Builder(WebClient client) {
            this.client = client;
        }

        public Builder message() {
            this.uri = "/message";
            return this;
        }

        public Builder cmd() {
            this.uri = "/cmd";
            return this;
        }

        public Builder body(Object obj) {
            this.body = obj;
            return this;
        }

        public Mono<GenericResponse> retrieve(String requestId) {
            return client.post()
                    .uri(this.uri)
                    .body(BodyInserters.fromObject(this.body))
                    .retrieve()
                    .bodyToMono(GenericResponse.class)
                    .map(v -> new GenericResponse(requestId, RPCResponse.STATUS.OK, Boolean.TRUE));
        }
    }
}
