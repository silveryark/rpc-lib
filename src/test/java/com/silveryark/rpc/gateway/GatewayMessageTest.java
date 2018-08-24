package com.silveryark.rpc.gateway;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GatewayMessageTest {

    @Test
    public void testInboundMessageWithId() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        InboundMessage inboundMessage = new InboundMessage(requestId, payload);
        Assert.assertEquals("requestId should equal.", requestId, inboundMessage.getRequestId());
        Assert.assertEquals("payload should equal.", payload, inboundMessage.getPayload());
    }

    @Test
    public void testInboundMessage() {
        String payload = RandomStringUtils.randomAlphanumeric(16);
        InboundMessage inboundMessage = new InboundMessage(payload);
        Assert.assertEquals("payload should equal.", payload, inboundMessage.getPayload());
    }

    @Test
    public void testOutboundMessageWithId() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String topic = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        OutboundMessage<String> outboundMessage = new OutboundMessage<>(requestId, topic, payload);
        Assert.assertEquals("topic should equal.", topic, outboundMessage.getTopic());
        Assert.assertEquals("requestId should equal.", requestId, outboundMessage.getRequestId());
        Assert.assertEquals("payload should equal.", payload, outboundMessage.getPayload());
    }

    @Test
    public void testOutboundMessage() {
        String topic = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        OutboundMessage<String> outboundMessage = new OutboundMessage<String>(topic, payload);
        Assert.assertNotNull("requestId should not be null", outboundMessage.getRequestId());
        Assert.assertEquals("topic should equal.", topic, outboundMessage.getTopic());
        Assert.assertEquals("payload should equal.", payload, outboundMessage.getPayload());
    }
}
