package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface Hy01Mapper extends Mapper<Hy01> {

	@Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('hya001'),6,0))")
	String autoKey();

	@Select("select * from hy01 where hya001=#{hya001}")
	Hy01 findById(String hya001);

	@Select("select * from hy01 where hya011=#{hya011}")
	Hy01 getHyInfoByNum(String hya011);

	@Update("update hy01 set hya007=#{hya007} where hya001=#{hya001}")
	int updateName(Hy01 hy01);


	@Update("update hy01 set hya018=#{hya018},hya019=#{hya019},hya020=#{hya020},hya037=#{hya037} where hya001=#{hya001}")
	int updateReserveInfo(Hy01 hy01);
}
