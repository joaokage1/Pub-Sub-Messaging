package Welness;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.toddy.jms.pubsub.hr.model.Employee;

public class WelnessApp {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			JMSConsumer consumer = jmsContext.createConsumer(topic);
			Message received = consumer.receive();
			Employee employee = received.getBody(Employee.class);

			System.out.println("Welness: " + employee.getFirstName() + " " + employee.getLastName());
		}
	}
}
