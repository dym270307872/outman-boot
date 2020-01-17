package cn.dyaoming.outman.activemq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@RestController
@RequestMapping("/demo")
public class DemoController {

	 @Autowired
	 private JmsTemplate jmsTemplate;
	 @Autowired
	 private Destination testQueueDestination;
	 @Autowired
	private Destination testTopicDestination;
	 @Autowired
	 private JmsMessagingTemplate jmsMessagingTemplate;
	
	@RequestMapping("/test")
	public String test(String value) {

		jmsTemplate.send(testQueueDestination, new MessageCreator()
		{
			@Override
			public Message createMessage(Session session) throws JMSException
			{
				return session.createTextMessage(value);
			}
		});
		return "success";
	}



	@RequestMapping("/test2")
	public String test2(String value) {

		jmsMessagingTemplate.convertAndSend("test2_queue",value);
		return "success";
	}


	@RequestMapping("/topic")
	public String topic(String value) {

		jmsTemplate.send(testTopicDestination, new MessageCreator()
		{
			@Override
			public Message createMessage(Session session) throws JMSException
			{
				return session.createTextMessage(value);
			}
		});
		return "success";
	}
}
