package com.silveryark.rpc.serializer;

import com.silveryark.rpc.gateway.OutboundMessage;

//Gateway向外发(to client)消息
public interface OutboundMessageSerializer extends Serializer<OutboundMessage> {

}
