package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.privatelife.wechatmall.mappers.Acb02Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Acb02;
import com.google.protobuf.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class Acb02Service extends BaseService {

	@Autowired
	private Acb02Mapper acb02Mapper;

	@Transactional
	@CacheEvict(value = "userInfo",key = "'checkBind:'+  #acb02.acbb003")
	public ApiResult toBind(Acb02 acb02)throws AppServiceException {
		ApiResult apiResult = new ApiResult();
		try {
			acb02.setAcbb001(acb02Mapper.autoKey());
			acb02.setIsvalid("1");
			acb02.setCreatetime(new Timestamp(new Date().getTime()));
			int i = acb02Mapper.insert(acb02);
			if(i!=1){
				throw new AppServiceException("9999");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return apiResult;
	}

	@Cacheable(value = "userInfo",key = "'checkBind:'+  #openId")
	public DataResult checkBind(String openId){
		DataResult dataResult = new DataResult();
		try {
			Acb02 acb02 = acb02Mapper.findBind(openId);
			if(acb02!=null){
				dataResult.setData(acb02);
			}else{
				dataResult = new DataResult(false,"9021");
			}
		} catch(Exception e) {
			dataResult = new DataResult(false,"9021");
		}
		return dataResult;
	}



//	@Cacheable(value ="userInfo",key = "'checkBindByHy:'+  #hya001")
	public DataResult checkBindByHy(String acbb002,String hya001){
		DataResult dataResult = new DataResult();
		try {
			Acb02 acb02 = acb02Mapper.findBindByHy(acbb002,hya001);
			if(acb02!=null){
				dataResult.setData(acb02);
			}else{
				dataResult = new DataResult(false,"9021");
			}
		} catch(Exception e) {
			dataResult = new DataResult(false,"9021");
		}
		return dataResult;
	}

	@Transactional
//	TODO 需要编写缓存逻辑刷新缓存。
	@CacheEvict(value = "userInfo",key = "'checkBind:'+  #acb02.acbb003")
//	@CacheEvict(value = "userInfo",key = "'checkBindByHy:'+  #acb02.hya001")
	public ApiResult unbind(Acb02 acb02){
		ApiResult apiResult = new ApiResult();
		try{

			acb02Mapper.toDelete(acb02);

		}catch (Exception e){
			e.printStackTrace();
			apiResult = new ApiResult(false,e.getMessage());
		}
		return apiResult;
	}
}
