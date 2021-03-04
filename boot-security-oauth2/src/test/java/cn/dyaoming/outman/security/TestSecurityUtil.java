package cn.dyaoming.outman.security;

import cn.dyaoming.outman.security.util.SecurityUtils;

public class TestSecurityUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "e10adc3949ba59abbe56e057f20f883e";
		String salt = SecurityUtils.createSalt();
		System.out.println("salt:" + salt);
		long start = System.currentTimeMillis();

		System.out.println("password:" + SecurityUtils.encodePassword(password, salt));
		long end = System.currentTimeMillis();
		System.out.println("encoding:" + (end - start));
	}

}
