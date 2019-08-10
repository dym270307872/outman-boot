package cn.dyaoming.privatelife.wechatmall.servers;

import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.privatelife.wechatmall.entitys.Sq01;
import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.utils.HttpRequestUtil;
import cn.dyaoming.utils.RandomUtil;
import cn.dyaoming.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatPayServer extends BaseServer {

    /**
     * @param appId    String类型 平台appId
     * @param mchId    String类型 微信商户号
     * @param orderId  String类型 订单号
     * @param totalFee BigDecimal类型 订单总额(元)
     * @return
     */
    public String unifiedorder(String openId,String appId, String mchId, String orderId, BigDecimal totalFee) {

        try {
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

            String random = RandomUtil.randomNumChar(32);
            totalFee = totalFee.multiply(new BigDecimal(100));
            int total = totalFee.intValue();


            String sign = "12345";
            String param = "<xml><appid>" + appId + "</appid>" +
                    "   <attach>支付测试</attach>" +
                    "   <body>天和鲜食汇-支付测试</body>" +
                    "   <mch_id>" + mchId + "</mch_id>" +
                    "   <nonce_str>"+random+"</nonce_str>" +
                    "   <notify_url></notify_url>" +
                    "   <openid>"+openId+"</openid>" +
                    "   <out_trade_no>"+orderId+"</out_trade_no>" +
                    "   <spbill_create_ip>14.23.150.211</spbill_create_ip>" +
                    "   <total_fee>"+total+"</total_fee>" +
                    "   <trade_type>JSAPI</trade_type>" +
                    "   <sign>"+sign+"</sign>" +
                    "</xml> ";

            return HttpRequestUtil.sendPost(url,param);

        } catch (Exception e) {

        }
        return "";
    }

}
