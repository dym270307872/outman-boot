package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.entitys.Sp01;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface Sp01Mapper extends Mapper<Sp01> {

    @Select("select * from sp01 where instr(spa005,#{goodsName})>0 and (spa003 = #{goodsType} or #{goodsType} = '') and  spa011 = '1' and  spa016 = '1' order by spa003 asc,spa005 asc ")
    List<Sp01> selectByType(@Param("goodsName") String goodsName,@Param("goodsType") String goodsType);


    @Select("select * from sp01 where spa001 = #{goodsId} and  spa016 = '1' ")
    Sp01 selectById(@Param("goodsId") String goodsId);
}
