package cn.dyaoming.privatelife.wechatmall.controllers;

import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.AccessService;
import cn.dyaoming.privatelife.wechatmall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;


@RestController
@RequestMapping("/api")
public class UserController  extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult register(String accessToken, String phoneNumber, String password) {
        if (isNull(accessToken) || isNull(phoneNumber) || isNull(password)) {
            return new ApiResult(false, "9015");
        }
        return userService.register(accessToken, phoneNumber,password);
    }


    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ApiResult getUserInfo(String accessToken, String openId) {
        if (isNull(accessToken) || isNull(openId)) {
            return new ApiResult(false, "9015");
        }
        return userService.getUserInfo(accessToken, openId);
    }



    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public ApiResult binding(String accessToken, String openId, String phoneNumber,
                             String password) {
        if (isNull(accessToken) || isNull(openId) || isNull(phoneNumber) || isNull(password)) {
            return new ApiResult(false, "9015");
        }
        return userService.binding(accessToken, openId, phoneNumber, password);
    }


    @RequestMapping(value = "/unbind", method = RequestMethod.POST)
    public ApiResult unbind(String accessToken, String openId,String password) {
        if (isNull(accessToken) || isNull(openId) || isNull(password)) {
            return new ApiResult(false, "9015");
        }
        return userService.unbind(accessToken, openId, password);
    }


    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    public ApiResult changeUserInfo(String accessToken, String openId,String changeType,String changeInfo) {
        if (isNull(accessToken) || isNull(openId) || isNull(changeType)|| isNull(changeInfo)) {
            return new ApiResult(false, "9015");
        }
        return userService.changeUserInfo(accessToken, openId, changeType,changeInfo);
    }
}