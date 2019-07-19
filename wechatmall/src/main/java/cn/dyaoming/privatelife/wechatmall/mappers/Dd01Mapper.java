package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Dd01;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface Dd01Mapper extends Mapper<Dd01> {

    //TODO 暂时写死的查询条件，后续需要调整
    @Select("select * from dd01 where dda002=#{dda002} /*and dda022='4'*/ and dda026='1' order by dda028 desc")
    List<Dd01> selectOrderList(@Param("dda002") String dda002,@Param("type") String type);



}
