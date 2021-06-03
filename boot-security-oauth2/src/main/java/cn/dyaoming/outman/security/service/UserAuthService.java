package cn.dyaoming.outman.security.service;

import org.springframework.stereotype.Component;

import cn.dyaoming.outman.security.model.UserInfo;
import cn.dyaoming.outman.security.util.SecurityUtils;

/**
 * <p>
 * 模拟用户服务
 * </p>
 * 
 * @author dym
 * @since 2021/03/04
 * @version 0.0.1
 */
@Component
public class UserAuthService {

	public boolean checkLogin(String userName, String password) {
		UserInfo userInfo = getUserInfo(userName);

		return userInfo.getPassword().equals(SecurityUtils.encodePassword(password, userInfo.getSalt()));
	}

	/**
	 * <p>
	 * 用户信息查询，模拟查询数据库
	 * </p>
	 *
	 * @param userName 用户编号
	 * @return 用户信息
	 */
	public UserInfo getUserInfo(String userName) {
		return new UserInfo("A0001", userName, "19c4bbfb9abd2847dcef5c64fac1b86a893b258074c31c94b5078dd66e28976c",
				"01f8cfaf882eba9745a51f8511e11f11a07192c68da2943a644037606bb7d8d6");
	}
}
