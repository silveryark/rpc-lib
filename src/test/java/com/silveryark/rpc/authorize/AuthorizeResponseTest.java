package com.silveryark.rpc.authorize;

import com.silveryark.rpc.RPCResponse;
import com.silveryark.rpc.authentication.AuthorizeResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthorizeResponseTest {

    @Test
    public void testBean() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        AuthorizeResponse authorizeResponse = new AuthorizeResponse(requestId, RPCResponse.STATUS.CLIENT_ERROR, payload);
        Assert.assertEquals("requestId should equal.", requestId, authorizeResponse.getRequestId());
        Assert.assertEquals("status should equal.", RPCResponse.STATUS.CLIENT_ERROR, authorizeResponse.getStatus());
        Assert.assertEquals("payload should equal.", payload, authorizeResponse.getPayload());
    }
}
