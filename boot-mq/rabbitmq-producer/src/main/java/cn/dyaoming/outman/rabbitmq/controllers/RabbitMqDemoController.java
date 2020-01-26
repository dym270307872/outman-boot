package cn.dyaoming.outman.rabbitmq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dyaoming.outman.rabbitmq.servers.RabbitSenderService;


@RestController
@RequestMapping("/rabbitMqDemo")
public class RabbitMqDemoController {

    @Autowired
    private RabbitSenderService rabbitSender;
	
    @RequestMapping("/unicast")
    public String unicast(String message) {

        rabbitSender.unicast(message);
        return "success";
    }


    @RequestMapping("/broadcast")
    public String broadcast(String message) {

        rabbitSender.broadcast(message);
        return "success";
    }
}
