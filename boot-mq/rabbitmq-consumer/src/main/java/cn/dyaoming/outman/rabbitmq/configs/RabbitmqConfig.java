package cn.dyaoming.outman.rabbitmq.configs;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * 类名称：RabbitmqConfig
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
public class RabbitmqConfig {

    @Bean
    public Queue unicast() {
        return new Queue("unicast") ;
    }

    @Bean
    public Queue broadcast() { return new Queue("topic.broadcast");}

    @Bean
    public FanoutExchange exchange() { return new FanoutExchange("exchange");}


    /**
     * 将队列"exchange" 绑定到交换机上，绑定规则是 broadcast
     *
     */
    @Bean
    public Binding bindingExchangeMessages(@Qualifier("broadcast") Queue broadcast, FanoutExchange exchange){
        return BindingBuilder.bind(broadcast).to(exchange);
    }
}