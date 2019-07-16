package cn.dyaoming.privatelife.wechatmall.controllers;

import cn.dyaoming.privatelife.wechatmall.services.AccessService;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController {

    @Autowired
    protected AccessService accessService;
}
