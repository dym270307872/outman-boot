package cn.dyaoming.privatelife.wechatmall.test;

import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.privatelife.wechatmall.servers.WeChatPayServer;
import cn.dyaoming.privatelife.wechatmall.servers.WeChatServer;
import com.alibaba.fastjson.JSON;
import javafx.scene.layout.BackgroundImage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TestWeChat extends BaseJunit {

    @Autowired
    private WeChatPayServer weChatPayServer;

    @Test
    public void min() {
        String appid = "wx160c890c070bf868";
//        String secret = "57e73b3eef309f063b52f900072f7e1c";
//        System.out.println(weChatServer.login(appid,"071cASKY1gM9OT0YkwIY1DIVKY1cASKL"));
//        System.out.println(JSON.toJSON(weChatAccess));

        System.out.println(weChatPayServer.unifiedorder("oeOsN5Kq24VpS_Oh_XwonvhxogBI",appid,"1549808951","00123123",new BigDecimal(0.01)));
    }
}
