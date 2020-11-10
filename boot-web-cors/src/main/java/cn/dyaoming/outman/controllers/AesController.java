package cn.dyaoming.outman.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.dyaoming.outman.util.AesUtil;
import cn.dyaoming.utils.Base64Util;

@RestController
@RequestMapping("/aes")
public class AesController {

	@RequestMapping("/encrypt")
	public String encrypt(String text, String key) {
		return Base64Util.encryptBASE64(AesUtil.encrypt(text, key)).replaceAll("\r\n", "");
	}

	@RequestMapping("/decrypt")
	public String decrypt(String text, String key) {

		try {
			return new String(AesUtil.decrypt(Base64Util.decryptBASE64(text), key), "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	@RequestMapping("/encrypt2")
	public Map<String,Object> encrypt2(String text, String key) {
		Map<String,Object> reMap = new HashMap<>();
		reMap.put("flag","true");
		reMap.put("result",Base64Util.encryptBASE64(AesUtil.encrypt(text, key)).replaceAll("\r\n", ""));
		return reMap;
	}
}
