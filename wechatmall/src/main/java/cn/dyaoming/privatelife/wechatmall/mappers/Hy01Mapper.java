package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface Hy01Mapper extends Mapper<Hy01> {

	@Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('hya001'),6,0))")
	String autoKey();

	@Select("select * from hy01 where hya001=#{hya001}")
	Hy01 findById(String hya001);

	@Select("select * from hy01 where hya011=#{hya011}")
	Hy01 getHyInfoByNum(String hya011);
}
