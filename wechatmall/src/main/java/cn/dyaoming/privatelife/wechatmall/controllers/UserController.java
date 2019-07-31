package cn.dyaoming.privatelife.wechatmall.controllers;

import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;


@RestController
@RequestMapping("/api")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult register(String openId, String phoneNumber, String password) {
        if (isNull(openId) || isNull(phoneNumber) || isNull(password)) {
            return new ApiResult(false, "9015");
        }
        return userService.register(openId, phoneNumber, password);
    }


    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ApiResult getUserInfo(String openId) {
        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.getUserInfo(openId);
    }


    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public ApiResult binding(String openId, String phoneNumber,
                             String password) {
        if (isNull(openId) || isNull(phoneNumber) || isNull(password)) {
            return new ApiResult(false, "9015");
        }
        return userService.binding(openId, phoneNumber, password);
    }


    @RequestMapping(value = "/unbind", method = RequestMethod.POST)
    public ApiResult unbind(String openId, String password) {
        if (isNull(openId) || isNull(password)) {
            return new ApiResult(false, "9015");
        }
        return userService.unbind(openId, password);
    }


    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    public ApiResult changeUserInfo(String openId, String changeType, String changeInfo) {
        if (isNull(openId) || isNull(changeType) || isNull(changeInfo)) {
            return new ApiResult(false, "9015");
        }
        return userService.changeUserInfo(openId, changeType, changeInfo);
    }


    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public ApiResult getBalance(String openId) {
        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.getBalance(openId);
    }


    @RequestMapping(value = "/getBalanceMx", method = RequestMethod.GET)
    public ApiResult getBalanceMx(String openId, String type,String pageNum) {
        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        if(isNull(pageNum)||"".equals(pageNum)){
            pageNum = "1";
        }else if(Integer.valueOf(pageNum)<1){
            pageNum = "1";
        }
        return userService.getBalanceMx(openId, type,Integer.valueOf(pageNum));
    }


    @RequestMapping(value = "/getReserveInfo", method = RequestMethod.GET)
    public ApiResult getReserveInfo(String openId) {
        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.getReserveInfo(openId);
    }


    @RequestMapping(value = "/changeReserveInfo", method = RequestMethod.POST)
    public ApiResult changeReserveInfo(String openId, String type, String state, String ydgz, String remarks) {
        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.changeReserveInfo(openId, type, state, ydgz, remarks);
    }

    @RequestMapping(value = "/getAddress", method = RequestMethod.GET)
    public ApiResult getAddress(String openId) {

        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.getAddress(openId);
    }


    @RequestMapping(value = "/changeAddress", method = RequestMethod.POST)
    public ApiResult changeAddress(String openId, String addressId, String mrbz, String name,String ssqy,  String phoneNum, String address) {

        if (isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.changeAddress(openId, addressId, mrbz, name, phoneNum,ssqy, address);
    }


    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    public ApiResult deleteAddress(String openId, String addressId) {

        if (isNull(openId) || isNull(addressId)) {
            return new ApiResult(false, "9015");
        }
        return userService.deleteAddress(openId, addressId);
    }


}
