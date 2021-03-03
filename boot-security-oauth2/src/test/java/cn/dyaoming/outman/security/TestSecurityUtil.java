package cn.dyaoming.outman.security;

import cn.dyaoming.outman.security.util.SecurityUtils;

public class TestSecurityUtil {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "123456";
		String salt = SecurityUtils.createSalt();
		long start = System.currentTimeMillis();
		
		System.out.println(SecurityUtils.encodePassword(password, salt,10));
		long end = System.currentTimeMillis();
		System.out.println("encoding:" +(end - start));
	}

}
