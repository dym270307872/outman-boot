package cn.dyaoming.privatelife.wechatmall.controllers;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api")
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public ApiResult getGoodsList(String accessToken, String goodsName, String goodsType, String type) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(accessToken)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getGoodsList(accessToken,goodsName,goodsType,type);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }


    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    public ApiResult getGoods(String accessToken, String goodsId) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(accessToken) || isNull(goodsId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getGoods(accessToken,goodsId);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }


    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    public ApiResult getOrderList(String accessToken, String openId,String type) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(accessToken) || isNull(openId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getOrderList(accessToken,openId,type);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }


    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public ApiResult getOrder(String accessToken, String openId,String orderId) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(accessToken) || isNull(openId) || isNull(orderId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getOrder(accessToken,openId,orderId);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }
}
