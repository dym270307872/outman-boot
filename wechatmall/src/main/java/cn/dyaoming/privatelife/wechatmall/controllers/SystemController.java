package cn.dyaoming.privatelife.wechatmall.controllers;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.LoginService;
import cn.dyaoming.privatelife.wechatmall.services.SystemService;
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
	@Autowired
	private LoginService  loginService;



	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResult login(String appId, String code) {

		if (isNull(appId) || isNull(code)) {
			return new ApiResult(false, "9015");
		}

		return loginService.login(appId, code);
	}



	@RequestMapping(value = "/getParam", method = RequestMethod.GET)
	public ApiResult getParam(String openId) {
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return systemService.getParam(openId);
		} catch(Exception e) {
			return new ApiResult(false,e.getMessage());
		}

	}



	@RequestMapping(value = "/getIndex", method = RequestMethod.GET)
	public ApiResult getIndex(String openId) {
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return systemService.getIndex(openId);
		} catch(Exception e) {
			return new ApiResult(false, e.getMessage());
		}

	}



	@RequestMapping(value = "/getPsDate", method = RequestMethod.GET)
	public ApiResult getPsDate(String openId) {
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return systemService.getPsDate(openId);
		} catch(Exception e) {
			return new ApiResult(false, e.getMessage());
		}

	}

}
