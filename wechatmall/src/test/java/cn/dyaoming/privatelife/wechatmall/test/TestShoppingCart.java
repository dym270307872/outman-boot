package cn.dyaoming.privatelife.wechatmall.test;

import cn.dyaoming.privatelife.wechatmall.services.ShopService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestShoppingCart extends BaseJunit{


    @Autowired
    private ShopService shopService;

    @Test
    public void query(){
/*

//        System.out.println("查询购物车:"+JSON.toJSONString(shopService.getShopCart("oeOsN5Kq24VpS_Oh_XwonvhxogBI")));
//
//        System.out.println("加入购物车:"+JSON.toJSONString(shopService.addShopCart("oeOsN5Kq24VpS_Oh_XwonvhxogBI","201707270021","1")));
//
//        System.out.println("查询购物车:"+JSON.toJSONString(shopService.getShopCart("oeOsN5Kq24VpS_Oh_XwonvhxogBI")));
//
//        System.out.println("修改购物车商品数量:"+JSON.toJSONString(shopService.changeShopCart("oeOsN5Kq24VpS_Oh_XwonvhxogBI","201707270021","7")));


        System.out.println("批量删除购物车商品:"+JSON.toJSONString(shopService.deleteShopCart("oeOsN5Kq24VpS_Oh_XwonvhxogBI","[\"201707270021\",\"201707270015\"]")));


        System.out.println("查询购物车:"+JSON.toJSONString(shopService.getShopCart("oeOsN5Kq24VpS_Oh_XwonvhxogBI")));
*/

        System.out.println(JSON.toJSONString(shopService.payOrder("otzm55UzEMcrRPImk5kahwnSPImk","20170731001431")));

    }


}
