package com.walmart.transformationservice.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String currHopStr = (String) exchange.getIn().getHeader("currHop");
        JSONObject currHopObj = new JSONObject(currHopStr);
        String destinationPreProcessing = (String) currHopObj.get("hopdest");
        String destination = destinationPreProcessing.substring(0, destinationPreProcessing.lastIndexOf("."));
        String hopmeta = currHopObj.getString("hopmeta");
        exchange.getIn().setHeader("destination", destination);
        exchange.getIn().setHeader("hopmeta", hopmeta);
    }
}
