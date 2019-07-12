package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface Sq01Mapper extends Mapper<Sq01> {

    @Select("select * from sq01 where isvalid='1' and sqa005=#{appId}")
    public Sq01 getSqInfo(String appId);
}
