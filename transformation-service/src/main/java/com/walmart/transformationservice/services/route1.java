package com.walmart.transformationservice.services;

import com.walmart.transformationservice.processors.DestProcessor;
import com.walmart.transformationservice.processors.TransHeaderProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class route1 extends RouteBuilder {

    @BeanInject
    private TransHeaderProcessor transHeaderProcessor;

    @BeanInject
    private DestProcessor destProcessor;

    @Override
    public void configure() throws Exception {
        from("mq:queue:TRANS")
                .process(transHeaderProcessor)
                .process(destProcessor)
                .toD("mq:queue:${headers.destination}");
    }
}
