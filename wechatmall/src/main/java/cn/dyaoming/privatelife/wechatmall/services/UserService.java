package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.models.HyInfo;
import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserService extends BaseService {

    @Autowired
    private Hy01Service hy01Service;
    @Autowired
    private Hy03Service hy03Service;
    @Autowired
    private Acb02Service acb02Service;


    public ApiResult register(String openId, String phoneNumber, String password) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                phoneNumber = getDecryptParam(openId, phoneNumber);
                password = getDecryptParam(openId, password);
            } else {
                return new DataResult(false, "9011");
            }

            apiResult = hy01Service.register(phoneNumber, password);

        } catch (Exception e) {

        }
        return apiResult;
    }


    public DataResult getUserInfo(String openId) {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                openId = getDecryptParam(openId, openId);
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


    public DataResult binding(String openId, String phoneNumber, String password) {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                phoneNumber = getDecryptParam(openId, phoneNumber);
                password = getDecryptParam(openId, password);
            } else {
                return new DataResult(false, "9011");
            }

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
                if (acb02Service.checkBindByHy(openId, phoneNumber).isFlag()) {
                    //用户账号已绑定其他微信
                    return new DataResult(false, "9024");
                } else {
                    //用户账号未绑定其他微信即用户和微信都处于未绑定状态
                    return hy01Service.binding(openId, phoneNumber, password);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, e.getMessage());
        }
        return dataResult;
    }


    public ApiResult unbind(String openId, String password) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                password = getDecryptParam(openId, password);
            } else {
                return new ApiResult(false, "9011");
            }
            //操作解绑逻辑
            apiResult = hy01Service.unbind(openId, password);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new DataResult(false, e.getMessage());
        }
        return apiResult;
    }


    public ApiResult changeUserInfo(String openId, String changeType, String changeInfo) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                changeType = getDecryptParam(openId, changeType);
                changeInfo = getDecryptParam(openId, changeInfo);

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


    public DataResult getBalance(String openId) {
        DataResult dataResult = new DataResult();
        try {
            if (!checkSession(openId)) {
                return new DataResult(false, "9011");
            }

            dataResult = hy01Service.getBalance(openId);
        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    public DataResult getBalanceMx(String openId, String type) {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                type = getDecryptParam(openId, type);
            } else {
                return new DataResult(false, "9011");
            }

            dataResult = hy01Service.getBalanceMx(openId, type);
        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    public DataResult getReserveInfo(String openId) {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                openId = getDecryptParam(openId, openId);

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


    public ApiResult changeReserveInfo(String openId, String type, String state, String ydgz, String remarks) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                type = getDecryptParam(openId, type);
                state = getDecryptParam(openId, state);
                ydgz = getDecryptParam(openId, ydgz);
                remarks = getDecryptParam(openId, remarks);

            } else {
                return new ApiResult(false, "9011");
            }
            if ("0".equals(state)) {
                return new ApiResult(false, "9015", "状态标识不能修改成未预定！");
            }


            apiResult = hy01Service.changeReserveInfo(openId, type, state, ydgz, remarks);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new ApiResult(false, "9999");
        }
        return apiResult;
    }


    public DataResult getAddress(String openId) {
        DataResult dataResult = new DataResult();
        try {
            if (!checkSession(openId)) {
                return new DataResult(false, "9011");
            }

            dataResult = hy03Service.getAddress(openId);
        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    public ApiResult changeAddress(String openId, String addressId, String mrbz, String name, String phoneNum, String address) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                addressId = getDecryptParam(openId, addressId);
                mrbz = getDecryptParam(openId, mrbz);
                name = getDecryptParam(openId, name);
                phoneNum = getDecryptParam(openId, phoneNum);
                address = getDecryptParam(openId, address);

            } else {
                return new ApiResult(false, "9011");
            }

            apiResult = hy03Service.changeAddress(openId, addressId, mrbz, name, phoneNum, address);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new ApiResult(false, "9999");
        }
        return apiResult;
    }


    public ApiResult deleteAddress(String openId, String addressId) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                addressId = getDecryptParam(openId, addressId);

            } else {
                return new ApiResult(false, "9011");
            }

            apiResult = hy03Service.deleteAddress(openId, addressId);
        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new ApiResult(false, "9999");
        }
        return apiResult;
    }


}
