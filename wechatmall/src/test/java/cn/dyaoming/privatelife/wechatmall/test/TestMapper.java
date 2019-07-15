package cn.dyaoming.privatelife.wechatmall.test;


import cn.dyaoming.privatelife.wechatmall.mappers.Hy01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.Base64Util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestMapper extends BaseJunit {


	@Test
	public void min() {
		String accessKey = "JvhPmyxqkVkyDKdH";
		System.out.println(Base64Util.encryptBASE64(AesUtil.encrypt("alkdjglkasdnlkas", "JvhPmyxqkVkyDKdH")));
		System.out.println(Base64Util.encryptBASE64(AesUtil.encrypt("13653866772", "JvhPmyxqkVkyDKdH")));
		System.out.println(Base64Util.encryptBASE64(AesUtil.encrypt("LM4L", "JvhPmyxqkVkyDKdH")));
	}
}
