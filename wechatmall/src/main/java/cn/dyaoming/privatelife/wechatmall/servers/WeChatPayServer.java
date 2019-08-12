package cn.dyaoming.privatelife.wechatmall.servers;


import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.privatelife.wechatmall.entitys.Sq01;
import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.privatelife.wechatmall.utils.WXPayUtil;
import cn.dyaoming.utils.HttpRequestUtil;
import cn.dyaoming.utils.RandomUtil;
import cn.dyaoming.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class WeChatPayServer extends BaseServer {

	/**
	 * @param openId   String类型 用户openId
	 * @param appId    String类型 平台appId
	 * @param mchId    String类型 微信商户号
	 * @param orderId  String类型 订单号
	 * @param totalFee BigDecimal类型 订单总额(元)
	 * @return
	 */
	public Map<String, String> unifiedorder(String openId, String appId, String mchId,
			String orderId,
			BigDecimal totalFee) {

		try {
			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

			String random = RandomUtil.randomNumChar(32);
			totalFee = totalFee.multiply(new BigDecimal(100));
			int total = totalFee.intValue();

			//根据微信支付api来设置
			Map<String, String> data = new HashMap<>();
			data.put("appid", appId);
			data.put("mch_id", mchId);         //商户号
			data.put("trade_type",
					"JSAPI");                         //支付场景 APP 微信app支付 JSAPI 公众号支付  NATIVE 扫码支付
			data.put("notify_url",
					"https://outman.eicp.vip/api/paymentCallback");                     //回调地址
			data.put("spbill_create_ip", "127.0.0.1");             //终端ip
			data.put("total_fee", String.valueOf(total));       //订单总金额
			data.put("fee_type", "CNY");                           //默认人民币
			data.put("out_trade_no", orderId);   //交易号
			data.put("openid", openId);//openid
			data.put("body", "天和鲜食汇-支付测试");
			data.put("nonce_str", RandomUtil.randomNumChar(32));   // 随机字符串小于32位
			String s = WXPayUtil.generateSignature(data, "TsPDZFsj1VsUdXUCkKHun9MBRpCECMnl");  //签名
			data.put("sign", s);
			System.out.println(data);
			String respXml = WXPayUtil.requestWithoutCert(url, data, 300, 300);
			Map<String, String> respData = WXPayUtil.xmlToMap(respXml);

			//验证返回结果签名
			if (WXPayUtil.isSignatureValid(respData, "TsPDZFsj1VsUdXUCkKHun9MBRpCECMnl")) {
				//获取需要字段
				Map result = new HashMap();
				result.put("timeStamp", Long.toString(System.currentTimeMillis() / 1000));
				result.put("nonceStr", RandomUtil.randomNumChar(32));
//				result.put("nonceStr",respData.get("nonce_str"));
				result.put("appId",appId);
				result.put("package", "prepay_id=" + respData.get("prepay_id"));
				result.put("signType", "MD5");
				String paySign = WXPayUtil
						.generateSignature(result, "TsPDZFsj1VsUdXUCkKHun9MBRpCECMnl");  //签名
				result.put("paySign", paySign);
				return result;
			} else {
				throw new AppServiceException("9999");
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppServiceException("9998");
		}
	}

}
