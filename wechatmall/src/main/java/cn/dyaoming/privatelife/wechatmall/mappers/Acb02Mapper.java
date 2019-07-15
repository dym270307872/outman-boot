package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Acb02;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface Acb02Mapper extends Mapper<Acb02> {

	@Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('acb001'),6,0))")
	String autoKey();

	@Select("select * from acb02 where acbb003=#{openId} and isvalid='1'")
	Acb02 findBind(String openId);

	@Select("select * from acb02 where acbb002=#{acbb002} and hya001=#{hya001} and isvalid='1'")
	Acb02 findBindByHy(String acbb002,String hya001);
}
