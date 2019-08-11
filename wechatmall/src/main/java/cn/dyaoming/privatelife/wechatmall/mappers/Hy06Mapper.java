package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.entitys.Hy06;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface Hy06Mapper extends Mapper<Hy06> {

	@Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('hyf001'),6,0))")
	String autoKey();

	@Select("select * from hy06 where hyf001 = #{hyf001}")
	Hy06 findById(@Param("hyf001") String hyf001);


	@Select("select * from hy06 where hyf002=#{hyf002} and hyf003= #{hyf003}")
	List<Hy06> findByOrderId(@Param("hyf003") String hya001,@Param("hyf002") String orderId);


	@Update("update hy06 set hyf008=#{hyf008},hyf009=#{hyf009},hyf014=#{hyf014},hyf016=#{hyf016},hyf017=#{hyf017},hyf018=#{hyf018} where hyf001=#{hyf001}")
	int updateById(Hy06 hy06);
}
