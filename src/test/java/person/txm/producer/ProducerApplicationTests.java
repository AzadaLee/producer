package person.txm.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import person.txm.producer.config.RabbitmqConifg;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {

	@Autowired
	private Producer producer;
	
	@Test
	public void directSend () {
		producer.directSend(RabbitmqConifg.QUEUE_NAME_TARS, "test direct send");
	}
	
	@Test
	public void testTopicSend () {
		producer.topicSend("this is topic send msg");
	}
	
	@Test
	public void testFanoutSend () {
		producer.fanoutSend("test fanout send msg");
	}
}
