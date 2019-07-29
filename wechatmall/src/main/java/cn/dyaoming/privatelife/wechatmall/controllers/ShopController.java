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
    public ApiResult getGoodsList(String openId, String goodsName, String goodsType, String type) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(openId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getGoodsList(openId,goodsName,goodsType,type);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }


    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    public ApiResult getGoods(String openId, String goodsId) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(openId) || isNull(goodsId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getGoods(openId,goodsId);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }


    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public ApiResult sendOrder(String openId, String name,String phoneNum,String address,String remark,String ydsj,String[] goodsList) {
        ApiResult apiResult = new ApiResult();
        try{
            //示例：goodsList： ["2019072821351|5","2019072821350|2","2019072821355|1","2019072821358|1"]
            if (isNull(openId)) {
                return new ApiResult(false, "9015");
            }else if(isNull(name)){
                return new ApiResult(false, "9015","收货人姓名不能为空");
            }else if(isNull(phoneNum)){
                return new ApiResult(false, "9015","收货人电话不能为空");
            }else if(isNull(address)){
                return new ApiResult(false, "9015","收货地址不能为空");
            }
            return shopService.sendOrder(openId,name,phoneNum,address,remark,ydsj,goodsList);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    public ApiResult getOrderList(String openId,String type,int pageNum) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(openId)) {
                return new ApiResult(false, "9015");
            }
			if(isNull(pageNum)||pageNum<1){
				pageNum = 1;
			}
            return shopService.getOrderList(openId,type,pageNum);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }


    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
    public ApiResult getOrderInfo(String openId,String orderId) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(openId) || isNull(orderId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getOrderInfo(openId,orderId);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }



    @RequestMapping(value = "/getSnapshot", method = RequestMethod.GET)
    public ApiResult getSnapshot(String openId, String snapshotId) {
        ApiResult apiResult = new ApiResult();
        try{
            if (isNull(openId) || isNull(snapshotId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.getSnapshot(openId,snapshotId);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = new ApiResult(false,"9999");
        }
        return apiResult;
    }
}
