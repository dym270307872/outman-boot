package cn.dyaoming.outman.activemq.configs;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;


/**
 *
 * 类名称：ActiveMQConfig
 * <P/>
 * 类描述： 消息队列控制类。
 * <P/>
 * 创建时间：2018-11-19
 * <P/>
 * 创建人： 董耀明
 * <P/>
 * 版本：V1.0
 *
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    @Bean
    public Queue testQueueDestination() {
        return new ActiveMQQueue("test_queue") ;
    }


    @Bean
    public Destination testTopicDestination() { return new ActiveMQTopic("test_topic_queue");}


    /**
     * JmsListener注解默认只接收queue消息,如果要接收topic消息,需要设置containerFactory
     */
    /*@Bean
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
        topicListenerContainer.setPubSubDomain(true);
        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        return topicListenerContainer;
    }*/

    @Bean(name = "queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory activeMQConnectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }

    @Bean(name = "topicListenerFactory")
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> topicListenerFactory(ConnectionFactory activeMQConnectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;

    }

}