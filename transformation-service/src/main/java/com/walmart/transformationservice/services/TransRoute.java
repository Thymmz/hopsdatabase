package com.walmart.transformationservice.services;

import com.walmart.transformationservice.processors.DestProcessor;
import com.walmart.transformationservice.processors.TransHeaderProcessor;
import com.walmart.transformationservice.processors.XsltTransProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransRoute extends RouteBuilder {

    @BeanInject
    private TransHeaderProcessor transHeaderProcessor;

    @BeanInject
    private DestProcessor destProcessor;

    @BeanInject
    private XsltTransProcessor xsltTransProcessor;

    @Override
    public void configure() throws Exception {
        from("mq:queue:TRANS")
                .process(transHeaderProcessor)
                .process(destProcessor)
                .process(xsltTransProcessor)
                .toD("mq:queue:${headers.destination}");
    }
}
