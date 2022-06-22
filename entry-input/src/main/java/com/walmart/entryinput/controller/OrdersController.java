package com.walmart.entryinput.controller;

import com.ibm.mq.jms.MQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/entryInput")
public class OrdersController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping
    public void createOrder(@RequestBody String orders, @RequestHeader Map<String, String> headers) throws JMSException {
        MQQueue orderRequestQueue = new MQQueue("ENTRY");
        jmsTemplate.send(orderRequestQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage =  session.createTextMessage(orders);
                textMessage.setStringProperty("INT_ID",headers.get("int_id"));
                textMessage.setStringProperty("INT_CC", headers.get("int_cc"));
                textMessage.setStringProperty("INT_INST", headers.get("int_inst"));
                System.out.println(textMessage);
                //System.out.println(headers);
                return textMessage;
            }
        });

    }
}
