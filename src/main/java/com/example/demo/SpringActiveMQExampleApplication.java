package com.example.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringActiveMQExampleApplication {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@GetMapping("send")
	public void sendMessage() {
		final String textMessage = "Hello" ;
		System.out.println("Sending message " + textMessage + "to queue - demo");
		jmsTemplate.convertAndSend("demo", textMessage);
	}
	
	@JmsListener(destination = "demo")
	@SendTo("outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			System.out.println("Data retrieved from MQ == " + messageData);
		}
		return messageData;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringActiveMQExampleApplication.class, args);
		
	}

}
