package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Dd02;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface Dd02Mapper extends Mapper<Dd02> {

    @Select("select ddb001 'goodsId',ddb005 'goodsName',ddb009 'amount',ddb011 'image' from dd02 where ddb002=#{ddb002} and ddb016='1'")
    List<Map> getChildren(@Param("ddb002") String ddb002);
}