package cn.dyaoming.outman.mapper;

import org.apache.ibatis.annotations.Insert;

import cn.dyaoming.outman.entity.TempDO;
import tk.mybatis.mapper.common.Mapper;

public interface TempMapper extends Mapper<TempDO>{

    @Insert("insert into temp(code,msg) values(#{code},#{msg})")
    int insertDO(TempDO tempDO);
}
