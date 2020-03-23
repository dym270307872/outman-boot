package cn.dyaoming.outman.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.kafka.servers.FlushCacheService;
import cn.dyaoming.outman.kafka.servers.KafkaSenderService;

@RestController
@RequestMapping("/flushCacheDemo")
public class FlushCacheDemoController {

    @Autowired
    private FlushCacheService flushCache;


    @RequestMapping("/channel")
    public String flushChannel(String id) {

        flushCache.flushChannel(id);
        return "success";
    }
    
    @RequestMapping("/service")
    public String flushService(String id) {

        flushCache.flushService(id);
        return "success";
    }
    
    @RequestMapping("/gateway")
    public String flushgateway(String id) {

        flushCache.flushgateway(id);
        return "success";
    }
    
    
    @RequestMapping("/gateway2")
    public String flushgateway2(String id) {

        flushCache.flushgateway2(id);
        return "success";
    }


}
