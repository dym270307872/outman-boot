package cn.dyaoming.privatelife.wechatmall.mappers;


import cn.dyaoming.privatelife.wechatmall.entitys.Hy01;
import cn.dyaoming.privatelife.wechatmall.entitys.Hy02;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;


public interface Hy02Mapper extends Mapper<Hy02> {

    @Select("select concat(DATE_FORMAT(sysdate(), '%Y%m%d'),LPAD(autokey('hyb001'),6,0))")
    String autoKey();

    @Select("select * from hy02 where hyb046='1' and hyb002=#{cardId}")
    Hy02 findByNum(@Param("cardId") String cardId);

    @Update("update hy02 set hyb031=sysdate(),hyb032=#{hyb032},hyb033=#{hyb033} where hyb001=#{hyb001}")
    int jh(Hy02 hy02);


    @Update("update hy02 set hyb027='3',hyb036=sysdate(),hyb037 = '1',hyb038=#{hyb038}  where hyb046='1' and hyb002 = #{hyb002}")
    int loseCard(Hy02 hy02);

}
