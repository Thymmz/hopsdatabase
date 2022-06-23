package com.walmart.transformationservice.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import java.net.URI;

@Component
public class RestOrcusProcessor implements Processor {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        String restUrl = (String) exchange.getIn().getHeader("hopmeta");
        URI restURI = new URI(restUrl);
        //System.out.println(restURI);
        String result = restTemplate.getForObject(restURI, String.class);
        exchange.getIn().setBody(result);
    }
}
