package cn.dyaoming.outman.security.util;

import java.security.SecureRandom;
import java.util.Random;

import cn.dyaoming.utils.HmacUtil;

public class SecurityUtils {

	private final static int DEFAULT_SALT_LENGTH = 64;
	private final static int DEFAULT_ENCODE_TIME = 5;

	public static String createSalt() {
		return createSalt(DEFAULT_SALT_LENGTH);
	}

	public static String createSalt(int length) {
		Random ranGen = new SecureRandom();
		byte[] saltBytes = new byte[length / 2];
		ranGen.nextBytes(saltBytes);
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < saltBytes.length; i++) {
			String hex = Integer.toHexString(0xff & saltBytes[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static String encodePassword(String password, String salt) {
		return encodePassword(password, salt, DEFAULT_ENCODE_TIME);
	}

	public static String encodePassword(String password, String salt, int encodetimes) {
		String hmacResult = password;
		for (int i = 0; i < encodetimes; i++) {
			hmacResult = HmacUtil.sha256(hmacResult, salt);
		}
		return hmacResult;
	}

}
