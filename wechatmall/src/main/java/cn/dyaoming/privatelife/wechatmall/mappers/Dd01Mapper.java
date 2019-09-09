package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.entitys.Dd01;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface Dd01Mapper extends Mapper<Dd01> {

    @Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('dda001'),6,0))")
    String autoKey();

    @Select("select * from dd01 where dda002=#{dda002} and dda022 not in ('5') and dda026='1' order by dda028 desc")
    List<Dd01> selectOrderListAll(@Param("dda002") String dda002);

    @Select("select * from dd01 where dda002=#{dda002} and dda017='0' and dda022 not in ('5') and dda026='1' order by dda028 desc")
    List<Dd01> selectOrderListWfk(@Param("dda002") String dda002);

    @Select("select * from dd01 where dda002=#{dda002} and dda017='1' and dda022 not in ('4','5') and dda026='1' order by dda028 desc")
    List<Dd01> selectOrderListPsz(@Param("dda002") String dda002);

    @Select("select * from dd01 where dda002=#{dda002} and dda022='4' and dda026='1' order by dda028 desc")
    List<Dd01> selectOrderListYwc(@Param("dda002") String dda002);


    @Select("select * from dd01 where dda002=#{dda002}  and dda001 = #{dda001} and dda026='1' order by dda028 desc")
    Dd01 selectById(@Param("dda002") String hya001,@Param("dda001") String orderId);


    @Update("update dd01 set dda022='5' where dda002=#{dda002}  and dda001 = #{dda001}/*and dda022='4'*/ and dda026='1'")
    int deleteById(@Param("dda002") String hya001,@Param("dda001") String orderId);

    @Update("update dd01 set dda017='1' where dda001=#{dda001}")
    int setPay(@Param("dda001") String orderId);
}
