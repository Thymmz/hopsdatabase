package com.walmart.transformationservice.services;

import com.walmart.transformationservice.processors.DestProcessor;
import com.walmart.transformationservice.processors.ExitHeaderProcessor;
import com.walmart.transformationservice.processors.TransHeaderProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExitRoute extends RouteBuilder {

    @BeanInject
    private ExitHeaderProcessor exitHeaderProcessor;

    @BeanInject
    private DestProcessor destProcessor;

    @Override
    public void configure() throws Exception {
        from("mq:queue:EXIT")
                .process(exitHeaderProcessor)
                .process(destProcessor)
                .log("${headers.destination}")
                .toD("mq:queue:${headers.destination}");
    }
}
