package com.walmart.transformationservice.services;

import com.walmart.transformationservice.processors.DestProcessor;
import com.walmart.transformationservice.processors.OrcusHeaderProcessor;
import com.walmart.transformationservice.processors.RestOrcusProcessor;
import com.walmart.transformationservice.processors.TransHeaderProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrcusRoute extends RouteBuilder {

    @BeanInject
    private OrcusHeaderProcessor orcusHeaderProcessor;

    @BeanInject
    private DestProcessor destProcessor;

    @BeanInject
    private RestOrcusProcessor restOrcusProcessor;

    @Override
    public void configure() throws Exception {
        from("mq:queue:ORCUS")
                .process(orcusHeaderProcessor)
                .process(destProcessor)
                .process(restOrcusProcessor)
                .log("${headers.destination}")
                .toD("mq:queue:${headers.destination}");
    }
}
