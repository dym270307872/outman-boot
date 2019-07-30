package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.entitys.Dd03;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface Dd03Mapper extends Mapper<Dd03> {

    @Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('ddc001'),6,0))")
    String autoKey();

    @Select("select ddc004 'goodsId',ddc005 'goodsName',ddc009 'amount',ddc011 'image',ddc008 'dj',ddc010 'gg' from dd03 where ddc002=#{ddc002} and ddc016='1'")
    List<Map> getMyCart(@Param("ddc002") String ddc002);


    @Select("select * from dd03 where ddc002=#{ddc002} and ddc004=#{ddc004} and ddc016='1'")
    Dd03 find(@Param("ddc002") String ddc002,@Param("ddc004") String ddc004);


    int batchDelete(@Param("ddc002")String ddc002,@Param("l_ddc004") List<String> l_ddc004);


    @Update("update dd03 set ddc009=#{ddc009} where ddc001=#{ddc001}")
    int updateSl(Dd03 dd03);
}
