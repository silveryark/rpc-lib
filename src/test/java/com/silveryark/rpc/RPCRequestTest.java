package com.silveryark.rpc;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RPCRequestTest {

    @Test
    public void testEqual() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        GenericRequest request = new GenericRequest(requestId, payload);
        GenericRequest request2 = new GenericRequest(requestId, payload);
        Assert.assertEquals("should be equal.", request, request2);
        Assert.assertEquals("should be equal.", request.hashCode(), request2.hashCode());
        Assert.assertEquals("should be equal.", request, request);
    }

    @Test
    public void testNotEqual() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        String payload2 = RandomStringUtils.randomAlphanumeric(16);
        GenericRequest request = new GenericRequest(requestId, payload);
        GenericRequest request2 = new GenericRequest(requestId, payload2);
        Assert.assertNotEquals("should not equal.", request, request2);
        Assert.assertNotEquals("should not equal.", request.hashCode(), request2.hashCode());
        Assert.assertNotEquals("should not equal.", request, null);
    }

}
