package com.silveryark.rpc.authentication;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthorizeRequestBeanTest {

    @Test
    public void testBean() {
        String username = RandomStringUtils.randomAlphanumeric(16);
        String password = RandomStringUtils.randomAlphanumeric(16);
        AuthorizeRequest authorizeRequest = new AuthorizeRequest(new AuthorizeRequest.Credential(username, password));
        Assert.assertEquals("username should equal.", username, authorizeRequest.getPayload().getUsername());
        Assert.assertEquals("password should equal.", password, authorizeRequest.getPayload().getPassword());
        Assert.assertNotNull("requestId should not be null", authorizeRequest.getRequestId());
    }
}
