package cn.dyaoming.privatelife.wechatmall.servers;

import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.privatelife.wechatmall.mappers.Sq01Mapper;
import cn.dyaoming.privatelife.wechatmall.entitys.Sq01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServer {
    @Autowired
    protected CacheInterface cacheDao;
    @Autowired
    private Sq01Mapper sq01Mapper;



    protected Sq01 getSqInfo(String appId){
        Sq01 sq01 = new Sq01();
        try{
            sq01 = sq01Mapper.getSqInfo(appId);
        }catch(Exception e){
            e.printStackTrace();
        }


        return sq01;
    }

	/*public ApiResult access(String appId) {
		DataResult dataResult = new DataResult();

		try {

			Sq01 sq01 = sq01Service.getSqInfo(appId);

			if (sq01 != null) {

				Map<String, Object> data = new HashMap<String, Object>();

				//生成访问 token、key
				String accessToken = UUID.randomUUID().toString().trim().replaceAll("-", "");
				String accessKey = RandomUtil.randomChar(16);
				data.put("accessToken", accessToken);
				data.put("accessKey", accessKey);
				dataResult.setData(data);

				//                cacheDao.setCacheObjectData(appId,data);
				Map<String, Object> cacheObj = new HashMap<String, Object>();
				cacheObj.put("sq01", sq01);
				cacheObj.put("accessKey", accessKey);
				cacheDao.setCacheObjectData("cache:access:" + accessToken, cacheObj, 1800L);
			} else {
				dataResult = new DataResult(false, "9011");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dataResult;
	}*/




   /* public boolean check(String accessToken) {
        return cacheDao.exists("cache:access:" + accessToken);
    }


    private String getKey(String accessToken) {
        Map<String, Object> map = (Map<String, Object>) cacheDao
                .getCacheData("cache:access:" + accessToken);
        return StringUtil.processNullString(map.get("accessKey"));
    }*/


    /*public Sq01 getSqInfo(String accessToken) {
        Map<String, Object> map = (Map<String, Object>) cacheDao
                .getCacheData("cache:access:" + accessToken);
        return (Sq01) map.get("sq01");
    }*/


    /*public String decrypt(String accessToken, String param) throws AppServiceException {
        try {
//			return new String(AesUtil.decrypt(Base64Util.decryptBASE64(param), getKey(accessToken)),"utf-8");
            if (param == null) {
                return "";
            } else {
                return param;
            }
        } catch (Exception e) {
            throw new AppServiceException("9013");
        }
    }*/
}
