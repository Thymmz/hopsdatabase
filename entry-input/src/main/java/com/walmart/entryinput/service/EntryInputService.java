package com.walmart.entryinput.service;


import org.apache.camel.Message;
import org.apache.camel.Produce;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.jms.MessageProducer;

public class EntryInputService extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("direct:start").transform().constant("Hello from camel").to("mq:ENTRY");
    }



}
