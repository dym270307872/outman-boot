package cn.dyaoming.outman.kafka.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.dyaoming.outman.kafka.model.MessageModel;


/**
 * 消息处理业务层示例
 * 
 * @author PC-DYM
 */
@Service
public class MessageService {

    private final Logger log = LoggerFactory.getLogger(getClass());



    /**
     * 消息处理业务方法
     * 
     * @param message 消息内容
     */
    public void exec(MessageModel message) {
        log.info("我收到一条信息，内容是：" + message.getMsg());
    }

}
