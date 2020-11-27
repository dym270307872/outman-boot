package cn.dyaoming.outman.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dyaoming.outman.exception.BaseRunTimeException;

/**
 * @author dym
 *
 */
@Service
public class DemoService {

	public String getA() {
		return "A";
	}

	@Transactional(rollbackFor = Exception.class)
	public String throwException(String param) {
		if ("B".equals(param)) {
			throw new BaseRunTimeException("出错了！"+param);
		}
		return "B";
	}
}
