package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Hy03;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface Hy03Mapper extends Mapper<Hy03> {

    @Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('hyc001'),6,0))")
    String autoKey();

    @Update("update hy03 set hyc005='0' where hyc016='1' and hyc002=#{hya001}")
    int chearMR(@Param("hya001") String hya001);

    @Select("select hyc001 'addressId',hyc005 'mrbz',hyc007 'name',hyc008 'phoneNum',hyc009 'address' from hy03 where hyc016='1' and hyc002=#{hya001} order by hyc005 desc,hyc001 asc")
    List<Map> selectByHy(@Param("hya001") String hya001);


    @Update("update hy03 set hyc005=#{mrbz},hyc007=#{name},hyc008=#{phoneNum},hyc009=#{address} where hyc001=#{hyc001} and hyc002=#{hya001}")
    int changeAddress(@Param("hya001") String hya001, @Param("hyc001") String hyc001,@Param("mrbz") String mrbz,@Param("name") String name,@Param("phoneNum") String phoneNum,@Param("address") String address);

    @Update("update hy03 set hyc016='0' where hyc001=#{hyc001} and hyc002=#{hya001}")
    int deleteById(@Param("hya001") String hya001, @Param("hyc001") String hyc001);
}
