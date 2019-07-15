package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Hy02;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface Hy02Mapper extends Mapper<Hy02> {

	@Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('hyb001'),6,0))")
	String autoKey();
}
