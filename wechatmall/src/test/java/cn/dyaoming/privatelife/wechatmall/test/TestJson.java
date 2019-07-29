package cn.dyaoming.privatelife.wechatmall.test;

import cn.dyaoming.models.DataResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import static java.util.Objects.isNull;

public class TestJson {


    public static void main(String[] args){

        String param = "{\"name\":\"董耀明\",\"phoneNum\":\"18603928785\",\"address\":\"河南省郑州市金水区\",\"remark\":\"快点送货\",\"ydsj\":\"2019-07-30\",\"goodsList\":[\"2019072821351|5\",\"2019072821350|2\",\"2019072821355|1\",\"2019072821358|1\"]}";

        //获取输入参数
        JSONObject map = JSON.parseObject(param);

        if(isNull(map.get("name"))){
            System.out.println("收货人姓名不能为空");
        }else if(isNull(map.get("phoneNum"))){
            System.out.println("收货人电话不能为空");
        }else if(isNull(map.get("address"))){
            System.out.println("收货地址不能为空");
        }

        JSONArray jsonArray = map.getJSONArray("goodsList");


        System.out.println(jsonArray);
    }
}
