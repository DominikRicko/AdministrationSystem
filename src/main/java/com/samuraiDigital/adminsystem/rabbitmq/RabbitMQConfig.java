package com.samuraiDigital.adminsystem.rabbitmq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class RabbitMQConfig {
	
	private Environment env;

	public RabbitMQConfig(Environment env) {
		super();
		this.env = env;
	}
	
	@Bean
	public ConnectionFactory connectionFactory() throws Exception{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connectionFactory.setHost(env.getProperty("mq.host").trim());
		connectionFactory.setPort(Integer.parseInt(env.getProperty("mq.port")));
		connectionFactory.setVirtualHost(env.getProperty("mq.vhost").trim());
		connectionFactory.setUsername(env.getProperty("mq.username").trim());
		connectionFactory.setPassword(env.getProperty("mq.password").trim());
		
		return connectionFactory;
	}
	
	@Bean
	public CachingConnectionFactory cachingConnectionFactory() throws Exception{
		return new CachingConnectionFactory(connectionFactory());
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
		rabbitTemplate.setChannelTransacted(true);
		return rabbitTemplate;
	}
	
	@Bean
	public AmqpAdmin amqpAdmin() throws Exception{
		return new RabbitAdmin(cachingConnectionFactory());
	}
	
	@Bean
	Queue queue() {
		String name = env.getProperty("mq.queue.name").trim();
		
		boolean durable = StringUtils.isNotBlank(env.getProperty("mq.queue.durable"))? Boolean.valueOf(env.getProperty("mq.queue.durable").trim()) : true;
		boolean exclusive = StringUtils.isNotBlank(env.getProperty("mq.queue.exclusive")) ? Boolean.valueOf(env.getProperty("mq.queue.exclusive").trim()) : false;
		boolean autoDelete = StringUtils.isNotBlank(env.getProperty("mq.queue.autoDelete")) ? Boolean.valueOf(env.getProperty("mq.queue.autoDelete").trim()) : false;
		
		return new Queue(name, durable, exclusive, autoDelete);
	}
	
	@Bean
	TopicExchange exchange() {
		String name = env.getProperty("mq.exchange.name").trim();
		boolean durable = StringUtils.isNotBlank(env.getProperty("mq.exchange.durable")) ? Boolean.valueOf(env.getProperty("mq.exchange.durable").trim()) : true;
		boolean autoDelete = StringUtils.isNotBlank(env.getProperty("mq.exchange.autoDelete")) ? Boolean.valueOf(env.getProperty("mq.exchange.autoDelete").trim()) : false;
		
		return new TopicExchange(name,durable,autoDelete);
	}
	
	@Bean
	Binding binding() {
		String routekey = env.getProperty("mq.routekey").trim();
		return BindingBuilder.bind(queue()).to(exchange()).with(routekey);
	}
}
