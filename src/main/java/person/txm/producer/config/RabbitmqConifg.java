package person.txm.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConifg {

	//定义queue
	public static final String QUEUE_NAME_TRANS = "trans";
	
	public static final String QUEUE_NAME_TARS = "tars";
	
	public static final String QUEUE_NAME_CMS = "cms";
	
	//定义exchange
	public static final String EX_NAME_DIRECT = "directEX";
	
	public static final String EX_NAME_TOPIC = "TOPICEX";
	
	public static final String EX_NAME_FANOUT = "FANOUTEX";
	
	/**
	 * exclusive true if we are declaring an exclusive queue (the queue will only be used by the declarer's
	 * connection)
	 * @return
	 */
	@Bean
	Queue transQueue () {
		return new Queue(QUEUE_NAME_TRANS, true, false, true);
	}
	
	@Bean
	Queue tarsQueue () {
		return new Queue(QUEUE_NAME_TARS, true, false, true);
	}
	
	@Bean
	Queue cmsQueue () {
		return new Queue(QUEUE_NAME_CMS, true, false, true);
	}
	
	@Bean
	DirectExchange directExchange () {
		return new DirectExchange(EX_NAME_DIRECT, true, true);
	}
	
	@Bean
	TopicExchange topicExchange () {
		return new TopicExchange(EX_NAME_TOPIC, true, true);
	}
	
	@Bean
	FanoutExchange fanoutExchange () {
		return new FanoutExchange(EX_NAME_FANOUT, true, true);
	}
	
	@Bean
	Binding bindTransToTopic () {
		return BindingBuilder.bind(transQueue()).to(topicExchange()).with("t.#");
	}
	
	@Bean
	Binding bindTarsToTopic () {
		return BindingBuilder.bind(tarsQueue()).to(topicExchange()).with("tars.#");
	}
	
	@Bean
	Binding bindcmsToTopic () {
		return BindingBuilder.bind(cmsQueue()).to(topicExchange()).with("cms.#");
	}
	
	/**
	 * <strong>如果topic进行模糊匹配，routingKey命名必须以key....形式</strong>
	 * @return
	 */
	@Bean
	Binding bindsToTopic () {
		return BindingBuilder.bind(tarsQueue()).to(topicExchange()).with("t.#");
	}
	
	@Bean
	Binding bindTarsToFanout () {
		return BindingBuilder.bind(tarsQueue()).to(fanoutExchange());
	}
	
	@Bean
	Binding bindTransToFanout () {
		return BindingBuilder.bind(transQueue()).to(fanoutExchange());
	}
	
	@Bean
	Binding bindCmsToFanout () {
		return BindingBuilder.bind(cmsQueue()).to(fanoutExchange());
	}
}
