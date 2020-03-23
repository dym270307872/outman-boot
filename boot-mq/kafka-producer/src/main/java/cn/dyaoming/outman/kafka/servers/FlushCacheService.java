package cn.dyaoming.outman.kafka.servers;

import java.util.Date;
import java.util.UUID;

import cn.dyaoming.outman.kafka.model.MessageBO;
import cn.dyaoming.outman.kafka.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class FlushCacheService {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void flushChannel(String channelId) {
        MessageBO message = new MessageBO();
        message.setId(channelId);
        message.setType("message1");
        kafkaTemplate.send("cis_policy_message", gson.toJson(message));
    }

    public void flushService(String serviceId) {
        MessageBO message = new MessageBO();
        message.setId(serviceId);
        message.setType("message2");
        kafkaTemplate.send("cis_policy_message", gson.toJson(message));
    }
    
    public void flushgateway(String serviceId) {
        MessageBO message = new MessageBO();
        message.setId(serviceId);
        message.setType("message4");
        kafkaTemplate.send("cis_gateway_message", gson.toJson(message));
    }
    
    public void flushgateway2(String serviceId) {
        MessageBO message = new MessageBO();
        message.setId(serviceId);
        message.setType("message5");
        kafkaTemplate.send("cis_gateway_message", gson.toJson(message));
    }
    
   

}