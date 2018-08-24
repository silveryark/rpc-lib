package com.silveryark.rpc.serializer;

import com.silveryark.rpc.gateway.InboundMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class InboundMessageSerializerTest {

    private InboundMessage inboundMessage;

    @Before
    public void init() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        inboundMessage = new InboundMessage(requestId, payload);
    }

    @Test
    public void testSerializer() throws IOException {
        InboundMessageJSONSerializer serializer = new InboundMessageJSONSerializer();
        byte[] serialize = serializer.serialize(inboundMessage);
        InboundMessage deserializedMessage = serializer.deserialize(serialize);
        Assert.assertEquals("inbound message should be equal.", inboundMessage, deserializedMessage);
    }

}
