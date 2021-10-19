package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

@Component
public class QueueReceiveController {
    @Autowired
    private JmsTemplate jmsTemplate;
    private static final String QUEUE_NAME = "siri-in";
    private static final String QUEUE_NAME_SEND = "siri-queue";
    
    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(User user) {
        logger.info("Received message: {}", user.getName());
        sendMessage(user.getName());
    }
    public void sendMessage(String message)
    {
    	String connectionString = "Endpoint=sb://apicentrics.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=f0j9monM6TRW+QUS+709zVqWcjAB8zaFpbAR/Tk2uJc=Endpoint=sb://apicentrics.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=f0j9monM6TRW+QUS+709zVqWcjAB8zaFpbAR/Tk2uJc=";
        // create a Service Bus Sender client for the queue 
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(QUEUE_NAME_SEND)
                .buildClient();
    
        // send one message to the queue
        senderClient.sendMessage(new ServiceBusMessage(message));
        System.out.println("Sent a single message to the queue: " + QUEUE_NAME_SEND);        
    }

}