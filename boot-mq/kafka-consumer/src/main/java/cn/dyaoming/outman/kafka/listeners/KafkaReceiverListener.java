package cn.dyaoming.outman.kafka.listeners;


import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import cn.dyaoming.outman.kafka.model.MessageModel;
import cn.dyaoming.outman.kafka.services.MessageService;


@Component
public class KafkaReceiverListener {
    private final Logger   log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageService messageService;

    @KafkaListener(topics = { "unicast" })
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            log.info("unicast"+"接到一条消息,消息内容"+message);
//            messageService.exec(message);
        }

    }



    @KafkaListener(topics = { "broadcast" },groupId = "${config.kafka.group-id}")
    public void listen2(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            log.info("broadcast"+"接到一条消息,消息内容"+message);
        }

    }
}