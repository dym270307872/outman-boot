package cn.dyaoming.outman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dyaoming.outman.exception.BaseRunTimeException;

/**
 * @author dym
 *
 */
@Service
public class DemoAService {

	@Autowired
	private DemoBService demoBService;
	
	public String getA() {
		return "A";
	}
	
	public String getB() {
		return "B";
	}

	@Transactional(rollbackFor = Exception.class)
	public String throwAException(String param) {
		throw new BaseRunTimeException("出错了！"+param);
	}
}
