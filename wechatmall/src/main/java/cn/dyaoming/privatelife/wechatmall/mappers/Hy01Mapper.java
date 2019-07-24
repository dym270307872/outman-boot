package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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

	@Select("select (select hya021 from hy01 where hya001=#{hya001}) 'ye',(select FORMAT(ifnull(sum(hyf011),0),2) from hy06 where hyf006 in ('00','01') and hyf017='1' and hyf003=#{hya001}) 'cz',(select FORMAT(ifnull(sum(hyf011),0),2) from hy06 where hyf006 = '02' and hyf002 not in (select t1.hyf002 from hy06 t1 where t1.hyf006='03'  and   t1.hyf003=#{hya001}) and hyf017='1' and hyf003=#{hya001}) 'xf'")
	Map findBalance(String hya001);

	@Select("select hyf006 'type',hyf011 'je',hyf005 'ye' ,(select CONCAT('消费备注：',bma006) from bm01 where bma001 = hyf020) 'bz',DATE_FORMAT(hyf018,'%Y-%m-%d %H:%i:%s') 'sj' from hy06 where hyf002 not in (select t1.hyf002 from hy06 t1 where t1.hyf006='03'  and   t1.hyf003=#{hya001}) and hyf017='1' and hyf006 = #{type} and  hyf003=#{hya001} order by hyf018 desc")
	List<Map> findBalanceMx(@Param("hya001") String hya001, @Param("type") String type);
}
