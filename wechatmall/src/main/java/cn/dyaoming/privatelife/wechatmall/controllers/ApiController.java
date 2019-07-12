package cn.dyaoming.privatelife.wechatmall.controllers;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AccessService accessService;

    @RequestMapping("/access")
    public ApiResult access(String appId){


        return accessService.check(appId);
    }

@RequestMapping("random")
    public String random(int n){
       return  accessService.random(n);
    }
}
