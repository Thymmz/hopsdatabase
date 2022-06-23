package com.walmart.transformationservice.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrcusHeaderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, Object> propMap = exchange.getIn().getHeaders();
        Map<String, Object> hopMap = propMap.entrySet().stream()
                .filter(x -> x.getKey().startsWith("hop_"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        //System.out.println(hopMap.keySet());
        String currhopKey = null;
        for (String k: hopMap.keySet()) {
            String hopVal = (String) hopMap.get(k);
            JSONObject hopValjson = new JSONObject(hopVal);
            if (hopValjson.get("hopname").equals("ORCUS")){
                currhopKey = k;
            }
        }
        String currHopStr = (String) hopMap.get(currhopKey);
        exchange.getIn().setHeader("currHop", currHopStr);
    }
}
