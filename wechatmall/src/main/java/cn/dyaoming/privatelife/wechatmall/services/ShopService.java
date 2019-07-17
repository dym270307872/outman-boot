package cn.dyaoming.privatelife.wechatmall.services;

import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Pt01Mapper;
import cn.dyaoming.privatelife.wechatmall.mappers.Sp01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Pt01;
import cn.dyaoming.privatelife.wechatmall.models.PtInfo;
import cn.dyaoming.privatelife.wechatmall.models.Sp01;
import cn.dyaoming.privatelife.wechatmall.models.SpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService extends BaseService {

    @Autowired
    private AccessService accessService;

    @Autowired
    private Sp01Mapper sp01Mapper;
    @Autowired
    private Pt01Mapper pt01Mapper;


    @Cacheable("publicInfo")
    public DataResult getGoodsList(String accessToken, String goodsName, String goodsType, String type) {
        DataResult dataResult = new DataResult();
        try {

            if (accessService.check(accessToken)) {
                goodsName = accessService.decrypt(accessToken, goodsName);
                goodsType = accessService.decrypt(accessToken, goodsType);
                type = accessService.decrypt(accessToken, type);
            } else {
                return new DataResult(false, "9011");
            }


            List<Pt01> pt01List = pt01Mapper.selectAll();
            List<Sp01> sp01List = null;
            if ("01".equals(type)) {

            } else {
                sp01List = sp01Mapper.selectByType(goodsName, goodsType);
            }

            if (sp01List != null) {

                List<SpInfo> l_spInfo = new ArrayList<SpInfo>();
                List<String> l_ptId = new ArrayList<String>();
                sp01List.stream().forEach(p -> {
                    l_spInfo.add(p.toInfo());
                    l_ptId.add(p.getSpa003());
                });

                List<PtInfo> l_ptInfo = new ArrayList<PtInfo>();
                l_ptId.stream().distinct().forEach((ptId) -> {
                    l_ptInfo.add(pt01List.stream().filter(f -> (ptId.equals(f.getPta001()))).findFirst().get().toInfo());
                });


                Map<String, Object> data = new HashMap<String, Object>();
                data.put("list", l_spInfo);
                data.put("type", l_ptInfo);
                dataResult.setData(data);
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }

    @Cacheable("publicInfo")
    public DataResult getGoods(String accessToken, String goodsId) {
        DataResult dataResult = new DataResult();
        try {

            if (accessService.check(accessToken)) {
                goodsId = accessService.decrypt(accessToken, goodsId);
            } else {
                return new DataResult(false, "9011");
            }

            dataResult.setData(sp01Mapper.selectById(goodsId).toMx());

        } catch (
                Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }



    @Cacheable("businessInfo")
    public DataResult getOrderList(String accessToken, String goodsId) {
        DataResult dataResult = new DataResult();
        try {

            if (accessService.check(accessToken)) {
                goodsId = accessService.decrypt(accessToken, goodsId);
            } else {
                return new DataResult(false, "9011");
            }

            dataResult.setData(sp01Mapper.selectById(goodsId).toMx());

        } catch (
                Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }



    @Cacheable("businessInfo")
    public DataResult getOrder(String accessToken, String goodsId) {
        DataResult dataResult = new DataResult();
        try {

            if (accessService.check(accessToken)) {
                goodsId = accessService.decrypt(accessToken, goodsId);
            } else {
                return new DataResult(false, "9011");
            }

            dataResult.setData(sp01Mapper.selectById(goodsId).toMx());

        } catch (
                Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


}
