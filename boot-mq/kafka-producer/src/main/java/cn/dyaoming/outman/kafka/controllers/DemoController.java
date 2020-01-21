package cn.dyaoming.outman.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.kafka.servers.KafkaSender;

@RestController
@RequestMapping("/kafkaDemo")
public class DemoController {

    @Autowired
    private KafkaSender kafkaSender;


    @RequestMapping("/unicast")
    public String unicast(String value) {

        kafkaSender.unicast();
        return "success";
    }


    @RequestMapping("/broadcast")
    public String broadcast(String value) {

        kafkaSender.broadcast();
        return "success";
    }

}
