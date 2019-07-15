package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.privatelife.wechatmall.mappers.Sq01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class Sq01Service extends BaseService{

	@Autowired
	private Sq01Mapper sq01Mapper;


	@Cacheable("systemInfo")
	public Sq01 getSqInfo(String appId){
		Sq01 sq01 = new Sq01();
		try{
			sq01 = sq01Mapper.getSqInfo(appId);
		}catch(Exception e){
			e.printStackTrace();
		}


		return sq01;
	}
}
