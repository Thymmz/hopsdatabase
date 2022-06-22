package com.walmart.entryservice.service;

import org.apache.camel.builder.RouteBuilder;

public class r1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:r1")
                .recipientList("m1:queue:${header.hopnext}");
    }
}
