package com.silveryark.rpc.serializer;

import com.silveryark.rpc.gateway.OutboundMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class OutboundMessageSerializerTest {

    private OutboundMessage<String> outboundMessage;

    @Before
    public void init() {
        String requestId = RandomStringUtils.randomAlphanumeric(16);
        String payload = RandomStringUtils.randomAlphanumeric(16);
        String topic = RandomStringUtils.randomAlphanumeric(16);
        String uid = RandomStringUtils.randomAlphanumeric(16);
        outboundMessage = new OutboundMessage<>(requestId, topic, uid, payload);
    }

    @Test
    public void testSerializer() throws IOException {
        OutboundMessageJSONSerializer serializer = new OutboundMessageJSONSerializer();
        byte[] serialize = serializer.serialize(outboundMessage);
        OutboundMessage deserializedMessage = serializer.deserialize(serialize);
        Assert.assertEquals("message should be equal.", outboundMessage, deserializedMessage);
    }

}
