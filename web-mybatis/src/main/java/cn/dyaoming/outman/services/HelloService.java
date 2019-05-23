package cn.dyaoming.outman.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dyaoming.outman.entitys.Temp;
import cn.dyaoming.outman.mappers.TempMapper;


/**
 * <p>hello业务层</p>
 * 
 * @author DYAOMING
 * @since 2019-04-26
 * @version V1.0
 */
@Service
@Transactional
public class HelloService {

	@Autowired
	private TempMapper tempMapper;



	/**
	 * <p>临时表全表查询</p>
	 * @return 临时表查询结果
	 */
	public List<Temp> list() {
		return tempMapper.findAll();
	}
}
