package com.silveryark.rpc;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RPCResponseTest {

    @Test
    public void testEqual() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        GenericResponse response = new GenericResponse(requestId, RPCResponse.STATUS.OK, payload);
        GenericResponse response2 = new GenericResponse(requestId, RPCResponse.STATUS.OK, payload);
        Assert.assertEquals("should be equal.", response, response2);
        Assert.assertEquals("should be equal.", response.hashCode(), response2.hashCode());
        Assert.assertEquals("should be equal.", response, response);
    }

    @Test
    public void testNotEqual() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        GenericResponse response = new GenericResponse(requestId, RPCResponse.STATUS.OK, payload);
        GenericResponse response2 = new GenericResponse(requestId, RPCResponse.STATUS.CLIENT_ERROR, payload);
        Assert.assertNotEquals("should not equal.", response, response2);
        Assert.assertNotEquals("should not equal.", response.hashCode(), response2.hashCode());
        Assert.assertNotEquals("should not equal.", response, null);
    }

    @Test
    public void testStatusCode() {
        Assert.assertEquals("should be 200", 200, RPCResponse.STATUS.OK.intValue());
        Assert.assertEquals("should be 400", 400, RPCResponse.STATUS.CLIENT_ERROR.intValue());
        Assert.assertEquals("should be 500", 500, RPCResponse.STATUS.SERVER_ERROR.intValue());
    }
}
