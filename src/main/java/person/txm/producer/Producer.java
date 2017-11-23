package person.txm.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import person.txm.producer.config.RabbitmqConifg;

@Component
public class Producer {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void topicSend (Object msg) {
		amqpTemplate.convertAndSend(RabbitmqConifg.EX_NAME_TOPIC, "t.dasdas", msg);
	}
	
	/**
	 * fanout类型的exchange默认routingKey为空
	 */
	public void fanoutSend (Object msg) {
		amqpTemplate.convertAndSend(RabbitmqConifg.EX_NAME_FANOUT, null, msg);
	}
}

