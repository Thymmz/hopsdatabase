//package com.walmart.entryservice.service;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.Message;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class EntryService extends RouteBuilder {
//    @Override
//    public void configure() throws Exception {
//        from("mq:queue:ENTRY")
//
//                .recipientList(simple("http://localhost:9020/api/v1/main?INT_ID=${header.INT_ID}&INT_CC=${header.INT_CC}&INT_INST=${header.INT_INST}"))
//                .to("log:body");
//
//        rest("http://localhost:9020/api/v1")
//                .get("/main?INT_ID=${header.INT_ID}&INT_CC=${header.INT_CC}&INT_INST=${header.INT_INST}")
//                .to("direct:process1");
//
//        from("direct:process1")
//                .process(new Processor() {
//                    public void process(Exchange exchange) throws Exception {
//                        String payload = exchange.getIn().getBody(String.class);
//                        // do something with the payload and/or exchange here
//                        exchange.getIn().setHeader("header",payload);
//                    }
//                });
//    }
//
//    public String calculateUri() {
//        return "http:...." + i;
//    }
//}


//                .process(new Processor() {
//           @Override
//            public void process(Exchange exchange) throws Exception {
//                Message in = exchange.getMessage();
//                Map<String, Object> headers = in.getHeaders();
//                List<String> keyList = Arrays.asList("INT_ID", "INT_CC", "INT_INST");
//                Map<String, Object> subMap = headers.entrySet().stream()
//                        .filter(x -> keyList.contains(x.getKey()))
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//            }
//        })
//        http://localhost:9021/api/v1/config?INT_ID=${header('INT_ID')}&INT_CC=${header('INT_CC')}&INT_INST=${header('INT_INST')}