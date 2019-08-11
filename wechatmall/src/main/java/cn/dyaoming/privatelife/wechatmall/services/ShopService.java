package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.entitys.*;
import cn.dyaoming.privatelife.wechatmall.mappers.*;
import cn.dyaoming.privatelife.wechatmall.models.*;
import cn.dyaoming.privatelife.wechatmall.servers.WeChatPayServer;
import cn.dyaoming.privatelife.wechatmall.utils.TimeUtil;
import cn.dyaoming.privatelife.wechatmall.utils.WXPayUtil;
import cn.dyaoming.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
public class ShopService extends BaseService {


    @Autowired
    private Sp01Mapper      sp01Mapper;
    @Autowired
    private Pt01Mapper      pt01Mapper;
    @Autowired
    private Dd01Mapper      dd01Mapper;
    @Autowired
    private Dd02Mapper      dd02Mapper;
    @Autowired
    private Dd03Mapper      dd03Mapper;
    @Autowired
    private Acb02Service    acb02Service;
    @Autowired
    private Hy01Service     hy01Service;
    @Autowired
    private Hy06Mapper     hy06Mapper;
    @Autowired
    private WeChatPayServer weChatPayServer;


    @Cacheable("publicInfo")
    public DataResult getGoodsList(String openId, String goodsName, String goodsType, String type) {
        DataResult dataResult = new DataResult();
        try {

            if (checkSession(openId)) {
                goodsName = getDecryptParam(openId, goodsName);
                goodsType = getDecryptParam(openId, goodsType);
                type = getDecryptParam(openId, type);
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
                    l_ptInfo.add(
                            pt01List.stream().filter(f -> (ptId.equals(f.getPta001()))).findFirst()
                                    .get().toInfo());
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
    public DataResult getGoods(String openId, String goodsId) {
        DataResult dataResult = new DataResult();
        try {

            if (checkSession(openId)) {
                goodsId = getDecryptParam(openId, goodsId);
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

    @Transactional
    public DataResult sendOrder(String openId, String param) {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                param = getDecryptParam(openId, param);
            } else {
                return new DataResult(false, "9011");
            }
            //获取输入参数
            JSONObject o_param = JSON.parseObject(param);

            if (isNull(o_param.get("name"))) {
                return new DataResult(false, "9015", "收货人姓名不能为空");
            } else if (isNull(o_param.get("phoneNum"))) {
                return new DataResult(false, "9015", "收货人电话不能为空");
            } else if (isNull(o_param.get("address"))) {
                return new DataResult(false, "9015", "收货地址不能为空");
            } else if (!checkDate(o_param.getString("ydsj"))) {
                return new DataResult(false, "9015", "预定时间格式不正确");
            } else if (isNull(o_param.getString("ssqy"))) {
                return new DataResult(false, "9015", "所属区域不能为空");
            }

            HyInfo hyInfo = (HyInfo) hy01Service.getUserInfo(openId).getData();
            String dda001 = dd01Mapper.autoKey();
            String ssqy = o_param.getString("ssqy").substring(o_param.getString("ssqy").lastIndexOf(":") + 1);
            //效验商品参数
            JSONArray jsonArray = o_param.getJSONArray("goodsList");

            List<BigDecimal> l_totle = new ArrayList<BigDecimal>();
            List<Dd02> dd02List = new ArrayList<Dd02>();
            try {
                jsonArray.forEach(p -> {
                    String[] goods = p.toString().split("\\|");
                    Sp01 sp01 = sp01Mapper.selectById(goods[0]);

                    Dd02 dd02 = new Dd02();
                    dd02.setDdb002(dda001);
                    dd02.setDdb004(sp01.getSpa001());
                    dd02.setDdb005(sp01.getSpa005());
                    dd02.setDdb006(sp01.getSpa006());
                    dd02.setDdb007(sp01.getSpa007());
                    dd02.setDdb008(sp01.getSpa008());
                    dd02.setDdb009(new BigDecimal(goods[1]));
                    dd02.setDdb010(sp01.getSpa009());
                    dd02.setDdb011(sp01.getSpa004());
                    dd02.setDdb016("1");
                    dd02.setDdb018(new Timestamp(new Date().getTime()));
                    dd02.setDdb019(hyInfo.getHyId());

                    dd02List.add(dd02);
                    l_totle.add(sp01.getSpa008().multiply(new BigDecimal(goods[1])));
                });
            } catch (Exception e) {
                return new DataResult(false, "9015", "查询不到商品信息");
            }
            BigDecimal totle = BigDecimal.ZERO;
            for (BigDecimal big : l_totle) {
                totle = totle.add(big);
            }

            //填充订单内容
            Dd01 dd01 = new Dd01();
            dd01.setDda001(dda001);
            dd01.setDda002(hyInfo.getHyId());
            dd01.setDda003(hyInfo.getHyCardId());
            dd01.setDda004(ssqy);
            dd01.setDda005("1");
            dd01.setDda006(o_param.getString("name"));
            dd01.setDda007(o_param.getString("phoneNum"));
            dd01.setDda008(o_param.getString("address"));
            dd01.setDda009(o_param.getString("remark"));
            dd01.setDda011(totle);

            dd01.setDda014(TimeUtil.parse(o_param.getString("ydsj"), "yyyy-MM-dd"));
            dd01.setDda015(hyInfo.getHyId());
            dd01.setDda017("0");
            dd01.setDda022("0");
            dd01.setDda023(new Timestamp(new Date().getTime()));
            dd01.setDda024(hyInfo.getHyId());
            dd01.setDda026("1");
            dd01.setDda028(new Timestamp(new Date().getTime()));
            dd01.setDda029(hyInfo.getHyId());
            //保存数据

            dd01Mapper.insert(dd01);
            dd02Mapper.batchInsert(dd02List);

//			返回订单编号
            Map map = new HashMap();
            map.put("orderId", dda001);
            dataResult.setData(map);

        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }

    @Transactional
    public DataResult payOrder(String openId,String orderId)throws AppServiceException{
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                orderId = getDecryptParam(openId, orderId);
            } else {
                return new DataResult(false, "9011");
            }

            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();
            Hy06 hy06 = new Hy06();
            //获取订单信息
            Dd01 dd01 = dd01Mapper.selectById(hya001,orderId);
            if("0".equals(dd01.getDda017())){

                //查询支付记录
                List<Hy06> o_hy06 = hy06Mapper.findByOrderId(hya001,orderId);

                if(o_hy06.size() == 0) {
                    //没有支付记录，依据订单生成
                    //判断支付金额及方式
                    HyInfo hyInfo = hy01Service.getHyInfo(hya001);

                    hy06.setHyf001(hy06Mapper.autoKey());
                    hy06.setHyf002(dd01.getDda001());
                    hy06.setHyf003(hyInfo.getHyId());
                    hy06.setHyf004(hyInfo.getHyCardId());
                    hy06.setHyf006("02");
                    hy06.setHyf011(dd01.getDda011());
                    hy06.setHyf018(new Timestamp(new Date().getTime()));
                    hy06.setHyf019(hyInfo.getHyId());

                    if (hyInfo.getYe().compareTo(dd01.getDda011()) >= 0) {
                        //余额充足
                        hy06.setHyf007("02");
                        hy06.setHyf017("1");

                        hy06.setHyf005(hyInfo.getYe().subtract(dd01.getDda011()));
                        hy06.setHyf012(dd01.getDda011());
                        hy06.setHyf013(new BigDecimal(0));
                        //更新dd01 支付标志为1,
                        dd01Mapper.setPay(orderId);
                    } else {
                        //余额不足
                        hy06.setHyf007("01");
                        hy06.setHyf017("0");
                        hy06.setHyf005(hyInfo.getYe().subtract(hyInfo.getYe()));
                        hy06.setHyf012(hyInfo.getYe());
                        hy06.setHyf013(dd01.getDda011().subtract(hyInfo.getYe()));
                    }
                    //更新hy01表中会员余额
                    hy01Service.setPay(hya001, hy06.getHyf012());
                    //插入hy06
                    hy06Mapper.insert(hy06);
                }else{
                    List<Hy06> hy06_dzf = o_hy06.stream().filter(f->("0".equals(f.getHyf017()))).collect(Collectors.toList());
                    if(hy06_dzf.size()==1){
                        hy06 = hy06_dzf.get(0);
                    }else{
                        //更新dd01 支付标志为1,
                        dd01Mapper.setPay(orderId);
                        return new DataResult(false,"9015","该订单已支付完成");
                    }
                }
            }else{
                return new DataResult(false,"9015","该订单已支付完成");
            }

//            处理返回结果

            Map<String, Object> result = new HashMap<String, Object>();

            Map<String, Object> hykzf = new HashMap<String, Object>();
            hykzf.put("zhcjje", hy06.getHyf012());
            hykzf.put("zhye", hy06.getHyf005());
            result.put("hykzf", hykzf);
            if (hy06.getHyf013().intValue()>0) {
                //获取微信支付参数
                String appId = StringUtil.processNullString(cacheDao.getCacheTData("cache:session:" + openId, Map.class).get("appid"));
                //调用微信统一下单接口
                //TODO 商户号写死的，如果动态调用需要调整
                Map<String, String> pay_result = weChatPayServer.unifiedorder(openId, appId, "1549808951", hy06.getHyf001(), hy06.getHyf013());
                result.put("weChatPay", pay_result);
//                System.out.println("微信支付订单");
            }
            dataResult.setData(result);
        }catch(Exception e){
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return dataResult;
    }

    @Transactional
    public Map<String, String> paymentCallback(Map<String, String> params){
        Map<String, String> return_data = new HashMap<String, String>();
        try {
            if (!WXPayUtil.isSignatureValid(params, "")) {
                // 支付失败
                return_data.put("return_code", "FAIL");
                return_data.put("return_msg", "return_code不正确");

            } else {
                System.out.println("===============付款成功==============");
                // ------------------------------
                // 处理业务开始
                // ------------------------------
                // 此处处理订单状态，结合自己的订单数据完成订单状态的更新
                // ------------------------------

                BigDecimal total_fee = new BigDecimal(Double.valueOf(params.get("total_fee"))/ 100);

                String hyf001 = String
                        .valueOf(Long.parseLong(params.get("out_trade_no").split("O")[0]));
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                Date accountTime = df.parse(params.get("time_end"));
                String appId = params.get("appid");
                String tradeNo = params.get("transaction_id");
                BigDecimal cash_fee = new BigDecimal(Double.valueOf(params.get("cash_fee"))/ 100);


                Long hyf016 = Long.parseLong(params.get("coupon_fee"));

                Hy06 hy06 = hy06Mapper.findById(hyf001);

                if(hy06.getHyf013().compareTo(total_fee)==0){
                    hy06.setHyf008("02");
                    hy06.setHyf009(tradeNo);
                    hy06.setHyf014(cash_fee);
                    hy06.setHyf016( hyf016);
                    hy06.setHyf017("1");
                    hy06.setHyf018(new Timestamp(accountTime.getTime()));

                    hy06Mapper.updateById(hy06);

                    return_data.put("return_code", "SUCCESS");
                    return_data.put("return_msg", "OK");
                }else{
                    return_data.put("return_code", "FAIL");
                    return_data.put("return_msg", "支付金额不匹配");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return return_data;
    }

    @Transactional
    public ApiResult deleteOrder(String openId, String orderId) {
        ApiResult apiResult = new ApiResult();
        try {
            if (checkSession(openId)) {
                orderId = getDecryptParam(openId, orderId);
            } else {
                return new ApiResult(false, "9011");
            }

            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            Dd01 dd01 = dd01Mapper.selectById(hya001,orderId);

            if(dd01!=null){

                if("0".equals(dd01.getDda022())){
                    dd01Mapper.deleteById(hya001,orderId);
                }else{
                    return new ApiResult(false, "9026","此订单已复核，不能取消订单，请联系客服取消！");
                }

            }else{
                return new ApiResult(false, "9031");
            }




        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new ApiResult(false, "9999");
        }
        return apiResult;
    }



    @Cacheable("businessInfo")
    public DataResult getOrderList(String openId, String type, int pageNum) {
        DataResult dataResult = new DataResult();
        try {

            if (checkSession(openId)) {
                type = getDecryptParam(openId, type);
            } else {
                return new DataResult(false, "9011");
            }

            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            PageHelper.startPage(pageNum, 10);
            List<Dd01> dd01List = dd01Mapper.selectOrderList(hya001, type);

            List<OrderList> orderLists = new ArrayList<OrderList>();

            dd01List.stream().forEach((p) -> {
                OrderList orderList = new OrderList();
                orderList.setOrderId(p.getDda001());
                orderList.setOrderType(p.getDda005());
                orderList.setTime(p.getDda028());
                orderList.setState(p.getDda022());
                orderList.setChildren(dd02Mapper.getChildren(p.getDda001()));
                orderList.setTotle(p.getDda011());
                orderLists.add(orderList);
            });

            dataResult.setData(orderLists);
        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    //	@Cacheable("businessInfo")
    public DataResult getOrderInfo(String openId, String orderId) {
        DataResult dataResult = new DataResult();
        try {

            if (checkSession(openId)) {
                orderId = getDecryptParam(openId, orderId);
            } else {
                return new DataResult(false, "9011");
            }

            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            Dd01 dd01 = dd01Mapper.selectById(hya001, orderId);

            if (dd01 != null) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId(dd01.getDda001());
                orderInfo.setOrderType(dd01.getDda005());
                orderInfo.setTime(dd01.getDda028());
                orderInfo.setState(dd01.getDda022());
                orderInfo.setChildren(dd02Mapper.getChildren(dd01.getDda001()));
                orderInfo.setTotle(dd01.getDda011());
                orderInfo.setDeliveryTime(dd01.getDda014());
                orderInfo.setRemarks(dd01.getDda009());
                orderInfo.setName(dd01.getDda006());
                orderInfo.setPhoneNum(dd01.getDda007());
                orderInfo.setAddress(dd01.getDda008());

                dataResult.setData(orderInfo);
            } else {
                dataResult = new DataResult(false, "9999");
            }

        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    @Cacheable("businessInfo")
    public DataResult getSnapshot(String openId, String snapshotId) throws AppServiceException {
        DataResult dataResult = new DataResult();
        try {

            if (checkSession(openId)) {
                snapshotId = getDecryptParam(openId, snapshotId);
            } else {
                throw new AppServiceException("9011");
            }

            dataResult.setData(dd02Mapper.selectById(snapshotId).toMx());

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return dataResult;
    }


    @Cacheable(value = "businessInfo", key = "'shopCart:' + #openId")
    public DataResult getShopCart(String openId) {
        DataResult dataResult = new DataResult();
        try {

            if (checkSession(openId)) {
            } else {
                return new DataResult(false, "9011");
            }
            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            dataResult.setData(dd03Mapper.getMyCart(hya001));

        } catch (Exception e) {
            e.printStackTrace();
            dataResult = new DataResult(false, "9999");
        }
        return dataResult;
    }


    @Transactional
    @CacheEvict(value = "businessInfo", key = "'shopCart:' + #openId")
    public ApiResult addShopCart(String openId, String goodsId, String amount) {
        ApiResult apiResult = new ApiResult();
        try {

            if (checkSession(openId)) {
                goodsId = getDecryptParam(openId, goodsId);
                amount = getDecryptParam(openId, amount);

                if (!checkNum(amount)) {
                    return new DataResult(false, "9015", "商品数量格式有误！");
                }
            } else {
                return new DataResult(false, "9011");
            }
            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            Dd03 dd03 = dd03Mapper.find(hya001, goodsId);

            if (dd03 != null) {
                dd03.setDdc009(dd03.getDdc009().add(new BigDecimal(amount)));
                dd03Mapper.updateSl(dd03);
            } else {

                Sp01 sp01 = sp01Mapper.selectById(goodsId);
                dd03 = new Dd03();
                dd03.setDdc001(dd03Mapper.autoKey());
                dd03.setDdc002(hya001);
                dd03.setDdc004(goodsId);
                dd03.setDdc005(sp01.getSpa005());
                dd03.setDdc006(sp01.getSpa006());
                dd03.setDdc007(sp01.getSpa007());
                dd03.setDdc008(sp01.getSpa008());
                dd03.setDdc009(new BigDecimal(amount));
                dd03.setDdc010(sp01.getSpa009());
                dd03.setDdc011(sp01.getSpa004());
                dd03.setDdc016("1");
                dd03.setDdc018(new Timestamp(new Date().getTime()));
                dd03.setDdc019(hya001);

                dd03Mapper.insert(dd03);
            }

        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new DataResult(false, "9999");
        }
        return apiResult;
    }

    @Transactional
    @CacheEvict(value = "businessInfo", key = "'shopCart:' + #openId")
    public ApiResult deleteShopCart(String openId, String goodsIds) {
        ApiResult apiResult = new ApiResult();
        try {

            if (checkSession(openId)) {
                goodsIds = getDecryptParam(openId, goodsIds);
            } else {
                return new DataResult(false, "9011");
            }
            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            JSONArray jsonArray = JSON.parseArray(goodsIds);

            List<String> l_goodsId = jsonArray.toJavaList(String.class);

            dd03Mapper.batchDelete(hya001, l_goodsId);

        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new DataResult(false, "9999");
        }
        return apiResult;
    }


    @Transactional
    @CacheEvict(value = "businessInfo", key = "'shopCart:' + #openId")
    public ApiResult changeShopCart(String openId, String goodsId, String amount) {
        ApiResult apiResult = new ApiResult();
        try {

            if (checkSession(openId)) {
                goodsId = getDecryptParam(openId, goodsId);
                amount = getDecryptParam(openId, amount);
                if (!checkNum(amount)) {
                    return new DataResult(false, "9015", "商品数量格式有误！");
                }
            } else {
                return new DataResult(false, "9011");
            }
            String hya001 = ((Acb02) acb02Service.checkBind(openId).getData()).getHya001();

            Dd03 dd03 = dd03Mapper.find(hya001, goodsId);

            if (dd03 != null) {
                dd03.setDdc009(new BigDecimal(amount));
                dd03Mapper.updateSl(dd03);
            } else {
                Sp01 sp01 = sp01Mapper.selectById(goodsId);
                dd03 = new Dd03();
                dd03.setDdc001(dd03Mapper.autoKey());
                dd03.setDdc002(hya001);
                dd03.setDdc004(goodsId);
                dd03.setDdc005(sp01.getSpa005());
                dd03.setDdc006(sp01.getSpa006());
                dd03.setDdc007(sp01.getSpa007());
                dd03.setDdc008(sp01.getSpa008());
                dd03.setDdc009(new BigDecimal(amount));
                dd03.setDdc010(sp01.getSpa009());
                dd03.setDdc011(sp01.getSpa004());
                dd03.setDdc016("1");
                dd03.setDdc018(new Timestamp(new Date().getTime()));
                dd03.setDdc019(hya001);

                dd03Mapper.insert(dd03);
            }

        } catch (Exception e) {
            e.printStackTrace();
            apiResult = new DataResult(false, "9999");
        }
        return apiResult;
    }

    private boolean checkDate(String date) {
        String regEx = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(date);
        return m.matches();
    }

    private boolean checkNum(String num) {
        String regEx = "^([0-9]*)|(([0-9]+)([.])([0-9]*))$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(num);
        return m.matches();
    }
}
