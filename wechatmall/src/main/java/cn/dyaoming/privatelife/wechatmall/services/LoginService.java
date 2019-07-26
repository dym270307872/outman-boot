package cn.dyaoming.privatelife.wechatmall.services;

import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.models.WeChatAccess;
import cn.dyaoming.utils.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class LoginService extends BaseService {


    //用户登录接口
    public ApiResult login(String appId, String j_code) {
        DataResult dataResult = new DataResult();
        try {
            String openId = weChatServer.login(appId,j_code);
            String accessKey = RandomUtil.randomChar(16);
            Map map = new HashMap();
            map.put("openId",openId);
            map.put("accessKey",accessKey);

            dataResult.setData(map);

        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, e.getMessage());
        }
        return dataResult;
    }
}
