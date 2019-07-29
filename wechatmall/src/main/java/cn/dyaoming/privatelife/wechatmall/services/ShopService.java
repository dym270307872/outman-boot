package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.*;
import cn.dyaoming.privatelife.wechatmall.models.*;
import cn.dyaoming.privatelife.wechatmall.utils.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.protobuf.Api;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;


@Service
public class ShopService extends BaseService {


	@Autowired
	private Sp01Mapper   sp01Mapper;
	@Autowired
	private Pt01Mapper   pt01Mapper;
	@Autowired
	private Dd01Mapper   dd01Mapper;
	@Autowired
	private Dd02Mapper   dd02Mapper;
	@Autowired
	private Acb02Service acb02Service;
	@Autowired
	private Hy01Service hy01Service;



	@Cacheable("publicInfo")
	public DataResult getGoodsList(String openId, String goodsName, String goodsType,
			String type) {
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

		} catch(
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

		} catch(
				Exception e) {
			e.printStackTrace();
			dataResult = new DataResult(false, "9999");
		}
		return dataResult;
	}

	@Transactional
	public DataResult sendOrder(String openId, String param){
		DataResult dataResult = new DataResult();
		try{
			if (checkSession(openId)) {
				param = getDecryptParam(openId, param);
			} else {
				return new DataResult(false, "9011");
			}
			//获取输入参数
			JSONObject map = JSON.parseObject(param);

			if(isNull(map.get("name"))){
				return new DataResult(false, "9015","收货人姓名不能为空");
			}else if(isNull(map.get("phoneNum"))){
				return new DataResult(false, "9015","收货人电话不能为空");
			}else if(isNull(map.get("address"))){
				return new DataResult(false, "9015","收货地址不能为空");
			}else if(!checkDate(map.getString("ydsj"))){
				return new DataResult(false, "9015","预定时间格式不正确");
			}

			HyInfo hyInfo =   (HyInfo)hy01Service.getUserInfo(openId).getData();
			//效验商品参数
			JSONArray jsonArray = map.getJSONArray("goodsList");



			//填充订单内容
			Dd01 dd01 = new Dd01();
			dd01.setDda001(dd01Mapper.autoKey());
			dd01.setDda002(hyInfo.getHyId());
			dd01.setDda003(hyInfo.getHyCardId());
			dd01.setDda005("1");
			dd01.setDda006(map.getString("name"));
			dd01.setDda007(map.getString("phoneNum"));
			dd01.setDda008(map.getString("address"));
			dd01.setDda009(map.getString("remark"));


			dd01.setDda014(TimeUtil.parse(map.getString("ydsj"),"yyyy-MM-dd"));
			dd01.setDda015(hyInfo.getHyId());

			//保存数据

//			返回订单编号






		}catch(Exception e){
 			e.printStackTrace();
		}
		return dataResult;
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
		} catch(Exception e) {
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

		} catch(Exception e) {
			e.printStackTrace();
			dataResult = new DataResult(false, "9999");
		}
		return dataResult;
	}



	@Cacheable("publicInfo")
	public DataResult getSnapshot(String openId, String snapshotId) {
		DataResult dataResult = new DataResult();
		try {

			if (checkSession(openId)) {
				snapshotId = getDecryptParam(openId, snapshotId);
			} else {
				return new DataResult(false, "9011");
			}

			dataResult.setData(dd02Mapper.selectById(snapshotId).toMx());

		} catch(Exception e) {
			e.printStackTrace();
			dataResult = new DataResult(false, "9999");
		}
		return dataResult;
	}


	private boolean checkDate(String date){
		String regEx = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(date);
		return m.matches();
	}
}
