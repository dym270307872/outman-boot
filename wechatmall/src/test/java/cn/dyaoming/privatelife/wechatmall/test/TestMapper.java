package cn.dyaoming.privatelife.wechatmall.test;


import cn.dyaoming.privatelife.wechatmall.mappers.Hy01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestMapper extends BaseJunit {

	@Autowired
	private Hy01Mapper hy01Mapper;

	@Test
	public void min(){

		for(Hy01 hy01:hy01Mapper.selectAll()){
			System.out.println(hy01.getHya002());
		}

	}
}
