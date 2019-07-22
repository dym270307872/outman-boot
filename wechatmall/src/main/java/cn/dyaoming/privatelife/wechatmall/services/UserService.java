package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.models.HyInfo;
import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService extends BaseService {

    @Autowired
    private AccessService accessService;
    @Autowired
    private Hy01Service hy01Service;
    @Autowired
    private Acb02Service acb02Service;


    public ApiResult register(String accessToken, String phoneNumber, String password) {
        ApiResult apiResult = new ApiResult();
        try {
            if (accessService.check(accessToken)) {
                phoneNumber = accessService.decrypt(accessToken, phoneNumber);
                password = accessService.decrypt(accessToken, password);
            } else {
                return new DataResult(false, "9011");
            }

            apiResult = hy01Service.register(phoneNumber, password);

        } catch (Exception e) {

        }
        return apiResult;
    }


    public DataResult getUserInfo(String accessToken, String openId) {
        DataResult dataResult = new DataResult();
        try {
            if (accessService.check(accessToken)) {
                openId = accessService.decrypt(accessToken, openId);
            } else {
                dataResult = new DataResult(false, "9011");
            }

            dataResult = hy01Service.getUserInfo(openId);

        } catch (Exception e) {
            //			e.printStackTrace();
            dataResult = new DataResult(false, e.getMessage());
        }
        return dataResult;
    }


    public DataResult binding(String accessToken, String openId, String phoneNumber,
                              String password) {
        DataResult dataResult = new DataResult();
        try {
            if (accessService.check(accessToken)) {
                openId = accessService.decrypt(accessToken, openId);
                phoneNumber = accessService.decrypt(accessToken, phoneNumber);
                password = accessService.decrypt(accessToken, password);

            } else {
                return new DataResult(false, "9011");
            }
            Sq01 sq01 = accessService.getSqInfo(accessToken);
            //判断是否已经绑定
            if (acb02Service.checkBind(openId).isFlag()) {
                //微信已绑定用户账号
                DataResult hyData = hy01Service.getUserInfo(openId);
                //判断绑定的用户账号是否与当前要绑定用户账号一致
                HyInfo hyInfo = (HyInfo) hyData.getData();
                if (phoneNumber.equals(hyInfo.getPhoneNum())) {
                    return hyData;
                } else {
                    return new DataResult(false, "9024");
                }

            } else {
                //未绑定，继续验证数据库判断用户账号是否绑定其他微信
                if (acb02Service.checkBindByHy(sq01.getSqa001(), phoneNumber).isFlag()) {
                    //用户账号已绑定其他微信
                    return new DataResult(false, "9024");
                } else {
                    //用户账号未绑定其他微信即用户和微信都处于未绑定状态
                    return hy01Service.binding(sq01.getSqa001(), openId, phoneNumber, password);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, e.getMessage());
        }
        return dataResult;
    }


    public ApiResult unbind(String accessToken, String openId, String password) {
        ApiResult apiResult = new ApiResult();
        try {
            if (accessService.check(accessToken)) {
                openId = accessService.decrypt(accessToken, openId);
                password = accessService.decrypt(accessToken, password);

            } else {
                return new ApiResult(false, "9011");
            }
            Sq01 sq01 = accessService.getSqInfo(accessToken);
            //操作解绑逻辑
            apiResult = hy01Service.unbind(sq01.getSqa001(), openId, password);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new DataResult(false, e.getMessage());
        }
        return apiResult;
    }


    public ApiResult changeUserInfo(String accessToken, String openId, String changeType, String changeInfo) {
        ApiResult apiResult = new ApiResult();
        try {
            if (accessService.check(accessToken)) {
                openId = accessService.decrypt(accessToken, openId);
                changeType = accessService.decrypt(accessToken, changeType);
                changeInfo = accessService.decrypt(accessToken, changeInfo);

            } else {
                return new ApiResult(false, "9011");
            }
            apiResult = hy01Service.changeInfo(openId, changeType, changeInfo);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new ApiResult(false, "9999");
        }
        return apiResult;
    }


    public DataResult getReserveInfo(String accessToken, String openId) {
        DataResult dataResult = new DataResult();
        try {
            if (accessService.check(accessToken)) {
                openId = accessService.decrypt(accessToken, openId);

            } else {
                return new DataResult(false, "9011");
            }

            dataResult = hy01Service.getReserveInfo(openId);
        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    public ApiResult changeReserveInfo(String accessToken, String openId, String type,String state,String ydgz,String remarks) {
        ApiResult apiResult = new ApiResult();
        try {
            if (accessService.check(accessToken)) {
                openId = accessService.decrypt(accessToken, openId);
                type = accessService.decrypt(accessToken, type);
                state = accessService.decrypt(accessToken, state);
                ydgz = accessService.decrypt(accessToken, ydgz);
                remarks = accessService.decrypt(accessToken, remarks);

            } else {
                return new ApiResult(false, "9011");
            }
            if("0".equals(state)){
                return new ApiResult(false, "9015","状态标识不能修改成未预定！");
            }


            apiResult = hy01Service.changeReserveInfo( openId, type,state,ydgz,remarks);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new ApiResult(false, "9999");
        }
        return apiResult;
    }

}
