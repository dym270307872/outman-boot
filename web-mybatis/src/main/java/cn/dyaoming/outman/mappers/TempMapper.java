package cn.dyaoming.outman.mappers;

import org.springframework.stereotype.Component;

import cn.dyaoming.outman.entitys.Temp;
import cn.dyaoming.plugins.jpa.repository.MyRepository;

/**
 * <p>临时表数据操作类</p>
 * 
 * @author DYAOMING
 * @since 2019-04-26
 * @version V1.0
 */
@Component
public interface TempMapper  extends MyRepository<Temp,String>{

}
