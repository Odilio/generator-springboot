package <%= packageName %>.adapters.inbound.message.consumer;

import <%= packageName %>.adapters.dto.<%= entityName %>DTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Service
public class <%= entityName %>Consumer implements MessageListener {

    private Logger log = LoggerFactory.getLogger(<%= entityName %>Consumer.class);

    @RabbitListener(queues = {"${<%= queueName %>}"})
    public void onMessage(Message message) {
        log.info("Mensagem recebida de ${message?.messageProperties?.consumerQueue}");
        val messageConverted = new String(message.getBody(), Charsets.ISO_8859_1);
        try {
            val mensagemTransporteVO = new ObjectMapper().readValue(messageConverted, <%= entityName %>DTO.class);

            if (mensagemTransporteVO != null) {
                log.info("Evento de origem ${mensagemTransporteVO.eventType} Conteudo da mensagem $messageConverted");
            }
        } catch (Exception e) {
            log.error("Erro ao converter mensagem", e);
        }
    }
}
