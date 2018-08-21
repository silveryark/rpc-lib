package com.silveryark.rpc.serializer;

import com.silveryark.rpc.gateway.InboundMessage;
import org.springframework.stereotype.Service;

//Gateway Request 的JSON实现
@Service
public class InboundMessageJSONSerializer
        extends AbstractGenericSerializer<InboundMessage>
        implements InboundMessageSerializer {

    InboundMessageJSONSerializer() {
        super(InboundMessage.class);
    }
}
