package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.privatelife.wechatmall.mappers.Hy01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private Hy01Mapper hy01Mapper;


	public Hy01 getUserInfo(){
		return hy01Mapper.selectByPrimaryKey("");
	}

}
