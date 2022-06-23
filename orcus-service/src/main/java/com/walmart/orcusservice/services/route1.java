package com.walmart.orcusservice.services;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class route1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("mq:queue:ORCUS")
                .to("log:${body}")
                .log("${headers}")
                .log("${body}");
    }
}
