package cn.dyaoming.privatelife.wechatmall.controllers;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.AccessService;
import cn.dyaoming.privatelife.wechatmall.services.SystemService;
import cn.dyaoming.privatelife.wechatmall.services.UserService;
import cn.dyaoming.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;


@RestController
@RequestMapping("/api")
public class SystemController extends BaseController {
    @Autowired
    private SystemService systemService;


    @RequestMapping(value = "/access", method = RequestMethod.POST)
    public ApiResult access(String appId) {

        if (isNull(appId)) {
            return new ApiResult(false, "9015");
        }

        return accessService.access(appId);
    }


    @RequestMapping(value = "/getParam", method = RequestMethod.GET)
    public ApiResult getParam(String accessToken) {

        if (isNull(accessToken)) {
            return new ApiResult(false, "9015");
        }
        return systemService.getParam(accessToken);
    }


    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public ApiResult getIndex(String accessToken) {

        if (isNull(accessToken)) {
            return new ApiResult(false, "9015");
        }
        return systemService.getIndex(accessToken);
    }


}
