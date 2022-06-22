package com.walmart.entryservice.service;

import com.ibm.mq.jms.MQQueue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.jms.*;
import java.util.*;

@Service
public class MessageListener {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @JmsListener(destination = "ENTRY")
    public void listen(Message message) throws JMSException {

        String INT_ID = message.getStringProperty("INT_ID");
        String INT_CC = message.getStringProperty("INT_CC");
        Integer INT_INST = Integer.valueOf(message.getStringProperty("INT_INST"));
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:9020/api/v1/main")
                .queryParam("INT_ID", "{INT_ID}")
                .queryParam("INT_CC", "{INT_CC}")
                .queryParam("INT_INST", "{INT_INST}")
                .encode()
                .toUriString();

        Map<String, Object> params = new HashMap<>();
        params.put("INT_ID", INT_ID);
        params.put("INT_CC", INT_CC);
        params.put("INT_INST", INT_INST);

        String result = restTemplate.getForObject(urlTemplate, String.class, params);
        JSONObject jsonObject = new JSONObject(result);

        JSONArray intseridarray = jsonObject.getJSONArray("intserid");
        //System.out.println(intseridarray);

        //String textMessage = message.getBody(String.class);
        message.clearProperties();

        Map<String, Object> headerMapfin = getHeadeMap(intseridarray);

        for (String k: headerMapfin.keySet()) {
            JSONObject headerObject = (JSONObject) headerMapfin.get(k);
            sendNewMessage(headerObject, message);
        }
        //System.out.println(headerMapfin);
    }

    private void sendNewMessage(JSONObject headerObject, Message message) throws JMSException {
        JSONObject destinationObject = headerObject.getJSONObject("0");
        String destinationString = (String) destinationObject.get("hopdest");
        destinationString.toUpperCase();
        String destination = destinationString.substring(0, destinationString.lastIndexOf("."));
        MQQueue mqQueue = new MQQueue(destination);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message1 = session.createTextMessage(message.getBody(String.class));
                headerObject.keySet().forEach(keyval -> {
                    try {
                        message1.setStringProperty(String.valueOf(keyval), String.valueOf(headerObject.getJSONObject(keyval)));
                    } catch (JMSException e) {
                    }
                });
            return message1;}
        });
        //System.out.println(destination);
    }

    private Map<String, Object> getHeadeMap(JSONArray intseridarray) {
        String hopLinkVal = getHoplinkVal(intseridarray);
        char numberOfMessages = getNumberOfMessages(hopLinkVal);
        Map<String, Object> headerMap = new HashMap<>();

        List<Object> headerList = new ArrayList<>();
        for (char ch = 'A'; ch <= numberOfMessages; ++ch)
            headerMap.put(String.valueOf(ch), new JSONObject());

        for (int i = 0; i < intseridarray.length(); i++) {
            JSONObject intseridele = (JSONObject) intseridarray.get(i);
            Set<String> keys = intseridele.keySet();
            keys.stream().findFirst();
            keys.forEach(key -> {
                JSONArray hoparr = intseridele.getJSONArray(key);
                for (int j = 0; j < hoparr.length(); j++) {
                    JSONObject hopsele = hoparr.getJSONObject(j);

                    String hopLinkValue = (String) hopsele.get("hoplink");
                    if (hopLinkValue.equals("0")){
                        for (String k : headerMap.keySet()) {
                            JSONObject headerObject = (JSONObject) headerMap.get(k);
                            headerObject.put("0", hopsele);
                            headerMap.put(k, headerObject);
                        }
                    }
                    else{
                        String heamapKey = String.valueOf(hopLinkValue.charAt(1));
                        JSONObject headerObject = (JSONObject) headerMap.get(heamapKey);
                        headerObject.put(String.valueOf(hopLinkValue.charAt(0)), hopsele);
                        headerMap.put(heamapKey, headerObject);
                    }
                }
            });
        }
        return headerMap;
    }

    private char getNumberOfMessages(String hopLinkVal) {
        if (hopLinkVal.length()==2){
            char charVal = hopLinkVal.charAt(1);
            //System.out.println(hopLinkVal.charAt(1)+"number of messages expected 2");
            return charVal;
        }
        else {
            return 'A';
        }
    }

    private Integer getNumberOfhops(String hopLinkVal) {
        //System.out.println(hopLinkVal.charAt(0)+"number of hops expected 2");
        return Integer.parseInt(String.valueOf(hopLinkVal.charAt(0)));
    }

    private String getHoplinkVal(JSONArray intseridarray) {
        JSONObject inseridele = intseridarray.getJSONObject(0);

        JSONArray hoparr = (JSONArray) inseridele.get(inseridele.keys().next());
        JSONObject hopObj = (JSONObject) hoparr.get(hoparr.length()-1);
        String hoplinkVal = (String) hopObj.get("hoplink");
        return hoplinkVal;
    }

    @SuppressWarnings("unchecked")
    private static HashMap<String, Object> getMessageProperties(Message msg) throws JMSException
    {
        HashMap<String, Object> properties = new HashMap<String, Object> ();
        Enumeration srcProperties = msg.getPropertyNames();
        while (srcProperties.hasMoreElements()) {
            String propertyName = (String)srcProperties.nextElement();
            properties.put(propertyName, msg.getObjectProperty(propertyName));
        }
        return properties;
    }

    private static void setMessageProperties(String msg, HashMap<String, Object> properties, String result) throws JMSException {
        if (properties == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String propertyName = entry.getKey ();
            Object value = entry.getValue ();
            //msg.setObjectProperty(propertyName, value);
        }
        //msg.setStringProperty("HopHeaders", result);
    }

}


