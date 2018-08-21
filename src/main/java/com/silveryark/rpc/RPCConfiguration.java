package com.silveryark.rpc;

import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RPCConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCConfiguration.class);

    @Bean
    public ErrorAttributes errorHandler() {
        LOGGER.info("initialize error handler");
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
                Map<String, Object> payload = super.getErrorAttributes(request, includeStackTrace);
                List<String> header = request.headers().header(RPCHttpHeaders.REQUEST_ID);
                String requestId = null;
                if (header != null && header.size() > 0) {
                    requestId = header.get(0);
                }
                RPCResponse.STATUS status = RPCResponse.STATUS.SERVER_ERROR;
                BeanMap beanMap = new BeanMap(new GenericResponse(requestId,
                        status,
                        payload));
                //因为属性里有status，spring框架里会把这个作为http的status返回给客户端，而在返回的时候会cast to int，所以这里需要
                //手转转一下int，不然enum就报错了
                Map<String, Object> retMap = new HashMap<>();
                for (Map.Entry<Object, Object> entry : beanMap.entrySet()) {
                    if (entry.getValue() != null) {
                        retMap.put(entry.getKey().toString(), entry.getValue());
                    }
                }
                retMap.put("status", status.intValue());
                //因为beanMap里会把class这个属性带上，所以去掉这个没用的东西
                retMap.remove("class");
                return retMap;
            }
        };
    }

}
