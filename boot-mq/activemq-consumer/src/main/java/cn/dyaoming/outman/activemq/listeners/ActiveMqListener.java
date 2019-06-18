package cn.dyaoming.outman.activemq.listeners;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;

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
@Service
public class ActiveMqListener {



    @JmsListener(destination="test_queue")
    public void testQueue(Message message)
    {

        //接受到文本格式日志，需要时可以强制转型
        TextMessage textMsg = (TextMessage) message;
        System.out.println(textMsg);

    }


    @JmsListener(destination="test2_queue")
    public void test2Queue(Message message)
    {

        //接受到文本格式日志，需要时可以强制转型
        TextMessage textMsg = (TextMessage) message;
        System.out.println(textMsg);

    }

    @JmsListener(destination="test_topic_queue",containerFactory = "topicListenerFactory")
    public void topicQueue(Message message)
    {

        //接受到文本格式日志，需要时可以强制转型
        TextMessage textMsg = (TextMessage) message;
        System.out.println("topic:"+textMsg);

    }
}