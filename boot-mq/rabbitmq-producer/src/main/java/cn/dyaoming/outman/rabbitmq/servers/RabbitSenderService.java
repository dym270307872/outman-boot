package cn.dyaoming.outman.rabbitmq.servers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitSenderService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    
    public void unicast(String message) {
        amqpTemplate.convertAndSend("unicast",message);
    }
    
    public void broadcast(String message) {
        amqpTemplate.convertAndSend("exchange","",message);
    }
}
