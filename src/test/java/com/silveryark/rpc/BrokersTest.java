package com.silveryark.rpc;

import com.google.common.net.MediaType;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Random;

import static org.mockserver.model.HttpRequest.request;

@RunWith(SpringRunner.class)
public class BrokersTest {

    private static ClientAndServer mockServer;
    private static int port;
    Brokers brokers = new Brokers();

    @AfterClass
    public static void destroyGlobal() {
        mockServer.stop();
    }

    @BeforeClass
    public static void setupGlobal() {
        port = new Random().nextInt(1024) + 1024;
        mockServer = ClientAndServer.startClientAndServer(port);
    }

    @Test
    public void testSendMessage() throws JSONException {
        MockServerClient block = testSend(true);
        Assert.notNull(block, "should return normally");
    }

    private MockServerClient testSend(boolean isMessage) throws JSONException {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        GenericRequest body = new GenericRequest(requestId, payload);
        new MockServerClient("localhost", port)
                .when(request()
                        .withMethod("POST"))
                .respond(HttpResponse.response().withBody(
                        new JSONObject()
                                .put("requestId", requestId)
                                .put("payload", payload)
                                .put("status", "OK").toString()
                        , MediaType.JSON_UTF_8));
        HttpRequest post = request()
                .withMethod("POST");
        final HttpRequest testPost;
        Brokers.Builder builder = brokers.create("localhost:" + port);
        if (isMessage) {
            testPost = post.withPath("/message");
            builder = builder.message();
        } else {
            testPost = post.withPath("/cmd");
            builder = builder.cmd();
        }
        return builder
                .body(body)
                .retrieve(requestId)
                .map(response -> {
                    return new MockServerClient("localhost", port)
                            .verify(testPost, VerificationTimes.exactly(1));
                }).block();
    }

    @Test
    public void testSendCmd() throws JSONException {
        MockServerClient block = testSend(false);
        Assert.notNull(block, "should return normally");
    }
}
