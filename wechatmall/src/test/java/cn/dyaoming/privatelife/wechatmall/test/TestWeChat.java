package cn.dyaoming.privatelife.wechatmall.test;

import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.privatelife.wechatmall.servers.WeChatServer;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestWeChat extends BaseJunit {

    @Autowired
    private WeChatServer weChatServer;

    @Test
    public void min() {
        String appid = "wxb4724704dfdf11e7";
//        String secret = "57e73b3eef309f063b52f900072f7e1c";
        System.out.println(weChatServer.login(appid,"071cASKY1gM9OT0YkwIY1DIVKY1cASKL"));
//        System.out.println(JSON.toJSON(weChatAccess));
    }
}
