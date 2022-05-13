package <%= packageName %>.adapters.inbound.message.producer;

import <%= packageName %>.adapters.dto.<%= entityName %>DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class <%= entityName %>Producer {

    private Logger log = LoggerFactory.getLogger(<%= entityName %>Producer.class);

    @Value("${<%= queueName %>}")
    private String <%= queueName %> = null;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendRabbit(<%= entityName %>DTO contratoAction) {
        if (<%= queueName %>.isBlank() == false) {
			log.info("Producer contrato $contratoAction");
		    rabbitTemplate.convertAndSend(<%= queueName %>, contratoAction);
		}
    }
}
