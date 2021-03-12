package com.toddy.jms.pubsub.hr;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.toddy.jms.pubsub.hr.model.Employee;

public class HRApp {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			Employee employee = new Employee(123, "Joao", "Silva", "jvgsilva180@gmail.com", "PA", "16988668540");
			JMSProducer producer = jmsContext.createProducer();
			producer.send(topic, employee);

			System.out.println("Message Sent: " + employee);
		}
	}
}
