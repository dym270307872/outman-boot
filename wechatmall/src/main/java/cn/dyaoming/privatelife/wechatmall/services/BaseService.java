package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.cache.interfaces.CacheInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BaseService {

	@Autowired
	protected CacheInterface cacheDao;
}
