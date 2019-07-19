package cn.dyaoming.privatelife.wechatmall.test;


import cn.dyaoming.privatelife.wechatmall.mappers.Dd02Mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class TestMapper extends BaseJunit {

	@Autowired
	private Dd02Mapper dd02Mapper;

	@Test
	public void min() {
		List<Map> list = dd02Mapper.getChildren("20170731001433");
		System.out.println(list);
	}
}
