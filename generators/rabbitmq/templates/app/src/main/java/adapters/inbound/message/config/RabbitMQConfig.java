package <%= packageName %>.adapters.inbound.message.config;

import <%= packageName %>.adapters.inbound.message.consumer.<%= entityName %>Consumer;
import org.aopalliance.aop.Advice;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;


@EnableRabbit
@Configuration
class RabbitMQConfig{
	 private ConnectionFactory connectionFactory;
	 private <%= entityName %>Consumer queueInstituicaoListener;

    @Value("${<%= queueName %>}")
    private String customers = null;

    @Bean
    public Queue <%= queueName %>Queue() {
        return new Queue("<%= queueName %>", true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding <%= queueName %>Binding(Queue <%= queueName %>, DirectExchange exchange) {
        return BindingBuilder.bind(<%= queueName %>).to(exchange).with("teste-routing-key");
    }
}
