package cn.dyaoming.privatelife.wechatmall.services;

import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.entitys.Acb02;
import cn.dyaoming.privatelife.wechatmall.entitys.Hy01;
import cn.dyaoming.privatelife.wechatmall.entitys.Hy02;
import cn.dyaoming.privatelife.wechatmall.mappers.Hy01Mapper;
import cn.dyaoming.privatelife.wechatmall.mappers.Hy02Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class CardService extends BaseService {

    @Autowired
    private Hy01Mapper hy01Mapper;
    @Autowired
    private Hy02Mapper hy02Mapper;
    @Autowired
    private Acb02Service acb02Service;


    @Transactional
    @CacheEvict(value = "userInfo", key = "'userInfo:'+  #openId")
    public ApiResult renewalCard(String openId, String cardId, String password) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                cardId = getDecryptParam(openId, cardId).toUpperCase();
                password = getDecryptParam(openId, password);
            } else {
                return new DataResult(false, "9021");
            }

            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            //判断新卡号是否存在
            Hy02 n_hy02 = hy02Mapper.findByNum(cardId);

            if (n_hy02 == null) {
                return new DataResult(false, "9997", "输入的新卡号不存在");
            } else if ("1".equals(n_hy02.getHyb032())) {
                return new DataResult(false, "9998", "新卡号已激活，不能进行绑定");
            } else if ("1".equals(n_hy02.getHyb037())) {
                return new DataResult(false, "9998", "新卡已挂失，不能进行绑定。");
            }
            //判断新卡密码是否正确
            if (!password.equals(n_hy02.getHyb003())) {
                return new DataResult(false, "9997", "您输入的卡密码有误，不能完成绑定。");
            }

            //操作绑定逻辑：1:更新会员信息；2、激活新卡
            Hy01 hy01 = new Hy01();
            hy01.setHya001(hya001);
            hy01.setHya002(cardId);
            hy01.setHya021(n_hy02.getHyb006());

            hy01Mapper.updateCard(hy01);

            n_hy02.setHyb032("1");
            n_hy02.setHyb033(hya001);

            hy02Mapper.jh(n_hy02);

        } catch (Exception e) {
//            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return apiResult;
    }


    @Transactional
    @CacheEvict(value = "userInfo", key = "'userInfo:'+  #openId")
    public ApiResult loseCard(String openId) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
            } else {
                return new DataResult(false, "9021");
            }
            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();
            hy01Mapper.loseCard(hya001);

            Hy01 hy01 = hy01Mapper.findById(hya001);
            Hy02 hy02 = new Hy02();
            hy02.setHyb002(hy01.getHya002());
            hy02.setHyb038(hya001);
            hy02Mapper.loseCard(hy02);

        } catch (Exception e) {
            throw new AppServiceException("9999");
        }
        return apiResult;
    }


    private boolean checkSession(String openId){
        if(checkAccess(openId)) {
            return acb02Service.checkBind(openId).isFlag();
        }
        return false;
    }

}
