package cn.dyaoming.outman.rabbitmq.listeners;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 *
 * 功能描述：消息队列监听类。
 * <P/>
 * 创建时间：2018-11-19
 * <P/>
 * 创建人： 董耀明
 * <P/>
 * 修改人：无
 * <P/>
 * 修改时间：无
 * <P/>
 * 修改备注：无
 *
 */
@Component

public class RabbitmqListener {

    @RabbitListener(queues = "unicast")
    public void unicast(String message) {
        System.out.println("unicast  : " + message);
    }

    @RabbitListener(queues = "topic.broadcast")
    public void broadcast(String message) {
        System.out.println("broadcast  : " + message);
    }

}