package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Hy03Mapper;
import cn.dyaoming.privatelife.wechatmall.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class Hy03Service extends BaseService {

    @Autowired
    private Hy03Mapper hy03Mapper;
    @Autowired
    private Acb02Service acb02Service;


    @Cacheable(value = "userInfo", key = "'address:'+#openId")
    public DataResult getAddress(String openId) throws AppServiceException {
        DataResult dataResult = new DataResult();
        try {
            DataResult bdInfo = acb02Service.checkBind(openId);

            if (bdInfo.isFlag()) {
                String hya001 = ((Acb02) bdInfo.getData()).getHya001();

                List<Map> l_address = hy03Mapper.selectByHy(hya001);
                dataResult.setData(l_address);
            } else {
                throw new AppServiceException("9999");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
//            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }

    @Transactional
    @CacheEvict(value = "userInfo", key = "'address:'+#openId")
    public ApiResult changeAddress(String openId, String addressId, String mrbz, String name, String phoneNum, String address) {
        ApiResult apiResult = new ApiResult();
        try {
            DataResult bdInfo = acb02Service.checkBind(openId);

            if (bdInfo.isFlag()) {
                String hya001 = ((Acb02) bdInfo.getData()).getHya001();

                if ("1".equals(mrbz)) {
                    hy03Mapper.chearMR(hya001);
                } else {
                    mrbz = "0";
                }

                if("".equals(addressId)){
                    Hy03 hy03 = new Hy03();
                    hy03.setHyc001(hy03Mapper.autoKey());
                    hy03.setHyc002(hya001);
                    hy03.setHyc005(mrbz);
                    hy03.setHyc007(name);
                    hy03.setHyc008(phoneNum);
                    hy03.setHyc009(address);
                    hy03.setHyc016("1");
                    hy03.setHyc018(new Timestamp(new Date().getTime()));
                    hy03.setHyc019(hya001);

                    hy03Mapper.insert(hy03);
                }else {


                    hy03Mapper.changeAddress(hya001, addressId, mrbz, name, phoneNum, address);
                }

            } else {
                throw new AppServiceException("9999");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return apiResult;
    }

    @Transactional
    @CacheEvict(value = "userInfo", key = "'address:'+#openId")
    public ApiResult deleteAddress(String openId, String addressId) {
        ApiResult apiResult = new ApiResult();
        try {
            DataResult bdInfo = acb02Service.checkBind(openId);

            if (bdInfo.isFlag()) {
                String hya001 = ((Acb02) bdInfo.getData()).getHya001();

                hy03Mapper.deleteById(hya001, addressId);
            } else {
                throw new AppServiceException("9999");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResult;
    }

}
