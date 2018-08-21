package com.silveryark.rpc.serializer;

import com.silveryark.rpc.gateway.OutboundMessage;
import org.springframework.stereotype.Service;

//使用JSON的方式序列化
@Service
public class OutboundMessageJSONSerializer
        extends AbstractGenericSerializer<OutboundMessage>
        implements OutboundMessageSerializer {

    OutboundMessageJSONSerializer() {
        super(OutboundMessage.class);
    }

}
