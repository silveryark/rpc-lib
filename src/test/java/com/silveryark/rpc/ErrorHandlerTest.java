package com.silveryark.rpc;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
public class ErrorHandlerTest {

    @Mock
    private ServerRequest request;

    @Mock
    private ServerRequest.Headers headers;

    @Mock
    private ServerWebExchange innerExchange;

    @Before
    public void before() {
        Mockito.when(headers.header(RPCHttpHeaders.REQUEST_ID))
                .thenReturn(Collections.singletonList(RandomStringUtils.randomAlphanumeric(16)));
        Map<String, Object> attributes = new HashMap<>();
        Mockito.when(innerExchange.getAttributes()).thenReturn(attributes);
        Mockito.when(request.headers()).thenReturn(headers);
        Mockito.when(request.path()).thenReturn("/test");
        Mockito.when(request.attribute(Mockito.anyString())).thenReturn(java.util.Optional.of(new RuntimeException()));
    }

    @Test
    public void testErrorHandler() {
        ErrorAttributes errorHandler =
                new RPCConfiguration().errorHandler();
        Map<String, Object> errorAttributes = errorHandler.getErrorAttributes(request, false);
        Set<String> keys = errorAttributes.keySet();
        Assert.assertTrue("should contain requestId", keys.contains("requestId"));
        Assert.assertTrue("should contain status", keys.contains("status"));
        Assert.assertTrue("status should be 500 error", errorAttributes.get("status").equals(500));
        Assert.assertTrue("should contain payload", keys.contains("payload"));
    }

    @After
    public void after() {
        Mockito.reset(request);
    }
}
