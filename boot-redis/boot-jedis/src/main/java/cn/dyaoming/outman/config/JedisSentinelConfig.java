package cn.dyaoming.outman.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

//@Configuration
public class JedisSentinelConfig extends CachingConfigurerSupport {
//    private Logger logger = LoggerFactory.getLogger(JedisSentinelConfig.class);

    /**
     * SpringSession  需要注意的就是redis需要2.8以上版本，然后开启事件通知，在redis配置文件里面加上
     * notify-keyspace-events Ex
     * Keyspace notifications功能默认是关闭的（默认地，Keyspace 时间通知功能是禁用的，因为它或多或少会使用一些CPU的资源）。
     * 或是使用如下命令：
     * redis-cli config set notify-keyspace-events Egx
     * 如果你的Redis不是你自己维护的，比如你是使用阿里云的Redis数据库，你不能够更改它的配置，那么可以使用如下方法：在applicationContext.xml中配置
     * <util:constant static-field="org.springframework.session.data.redis.config.ConfigureRedisAction.NO_OP"/>
     *
     * @return
     */

//    @Value("${spring.redis.host}")
//    private String host;

    @Value("${redis.sentinel.master}")
    private String masterName;

    @Value("${redis.sentinel.nodes}")
    private String nodes;

//    @Value("${spring.redis.port}")
//    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;


    @Bean
    public JedisSentinelPool jedisSentinelPool() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);

        String[] serverArray = nodes.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<String> nodes = new HashSet<String>();

        for (String ipPort : serverArray) {
            if (!ipPort.trim().isEmpty()) {
                nodes.add(ipPort);
            }
        }

        JedisSentinelPool jedisSentinePool = new JedisSentinelPool(masterName, nodes, jedisPoolConfig, timeout, "master");

        return jedisSentinePool;
    }

}