package com.walmart.orcusservice.config;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.camel.component.jms.JmsComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JmsConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(JmsConfiguration.class);
    private String host = "169.60.46.60";
    private int port = 1414;
    private String queueManager = "mq";
    private String channel = "DEV.ADMIN.SVRCONN";
    private String username = "admin";
    private String password = "Miracle@1234";
    private long receiveTimeout = 2000;

    @Bean
    public JmsComponent mq(){
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(mqQueueConnectionFactory());
        jmsComponent.setUsername(username);
        jmsComponent.setPassword(password);
        return jmsComponent;
    }

    @Bean
    public MQQueueConnectionFactory mqQueueConnectionFactory() {
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName(host);
        try {
            mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqQueueConnectionFactory.setChannel(channel);
            mqQueueConnectionFactory.setPort(port);
            mqQueueConnectionFactory.setQueueManager(queueManager);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return mqQueueConnectionFactory;
    }
}
