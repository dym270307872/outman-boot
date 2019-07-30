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
    public ApiResult sendOrder(String openId, String param) {
        ApiResult apiResult = new ApiResult();
        try{
            //param示例：{"name":"董耀明","phoneNum":"18603928785","address":"河南省郑州市金水区","remark":"快点送货","ydsj":"2019-07-30","goodsList":["2019072821351|5","2019072821350|2","2019072821355|1","2019072821358|1"]}
//            param = "{\"name\":\"董耀明\",\"phoneNum\":\"18603928785\",\"address\":\"河南省郑州市金水区\",\"remark\":\"快点送货\",\"ydsj\":\"2019-07-30\",\"goodsList\":[\"2019072821351|5\",\"2019072821350|2\",\"2019072821355|1\",\"2019072821358|1\"]}";
            if (isNull(openId)) {
                return new ApiResult(false, "9015");
            }
            return shopService.sendOrder(openId,param);
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
