package cn.dyaoming.privatelife.wechatmall.servers;

import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.privatelife.wechatmall.entitys.Sq01;
import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.utils.HttpRequestUtil;
import cn.dyaoming.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServer extends BaseServer {


    private WeChatAccess access(String appId) throws AppServiceException {
        try {
            if (cacheDao.exists("cache:access:" + appId)) {
                return cacheDao.getCacheTData("cache:access:" + appId, WeChatAccess.class);
            } else {
                Sq01 sq01 = getSqInfo(appId);
                if (sq01 != null) {
                    String url = "https://api.weixin.qq.com/cgi-bin/token";
                    Map map = new HashMap();
                    map.put("grant_type", "client_credential");
                    map.put("appid", sq01.getSqa005());
                    map.put("secret", sq01.getSqa007());

                    String result = HttpRequestUtil.sendGet(url, map);

                    WeChatAccess weChatAccess = JSON.parseObject(result).toJavaObject(WeChatAccess.class);

                    if ("".equals(weChatAccess.getAccess_token())) {
                        throw new AppServiceException("9011");
                    } else {
                        weChatAccess.setApp_id(sq01.getSqa005());
                        weChatAccess.setApp_secret(sq01.getSqa007());
                    }
                    cacheDao.setCacheObjectData("cache:access:" + appId, weChatAccess, weChatAccess.getExpires_in() - 120);
                    return weChatAccess;
                } else {
                    throw new AppServiceException("9011");
                }
            }
        } catch (Exception e) {
//			e.printStackTrace();
            throw new AppServiceException("9999");
        }
    }


    private void flushAccess(String appId) {
        cacheDao.deleteCacheData("cache:access:" + appId);
    }


    public String login(String appId, String j_code) throws AppServiceException {
        try {
            Sq01 sq01 = getSqInfo(appId);
            String url = "https://api.weixin.qq.com/sns/jscode2session";

            Map map = new HashMap();
            map.put("appid", sq01.getSqa005());
            map.put("secret", sq01.getSqa007());
            map.put("js_code", j_code);
            map.put("grant_type", "authorization_code");

            String result = HttpRequestUtil.sendGet(url, map);
            Map rm = JSON.parseObject(result);
            if (rm.get("openid") == null) {
//                System.out.println(result);
                throw new AppServiceException("9011");
            } else {
                String session_key = StringUtil.processNullString(rm.get("session_key"));
                String openid = StringUtil.processNullString(rm.get("openid"));
                Map session_map = new HashMap();
                session_map.put("sqa001", sq01.getSqa001());
                session_map.put("appid", sq01.getSqa005());
                session_map.put("session_key", session_key);

                cacheDao.setCacheObjectData("cache:session:" + openid, session_map, 1800, true);

                return openid;
            }

        }catch (AppServiceException e){
            throw new AppServiceException(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
    }



    private String getSession(String openId) {
        return StringUtil.processNullString((cacheDao.getCacheTData("cache:session:" + openId,Map.class).get("session_key")));
    }

}
