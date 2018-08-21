package com.silveryark.rpc.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silveryark.rpc.RPCRequest;

public class AuthorizeRequest extends RPCRequest<AuthorizeRequest.Credential> {

    public AuthorizeRequest(@JsonProperty("payload") Credential payload) {
        super(payload);
    }

    public static class Credential{
        private String username;
        private String password;

        @JsonCreator
        public Credential(@JsonProperty("username") String username,@JsonProperty("password") String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
