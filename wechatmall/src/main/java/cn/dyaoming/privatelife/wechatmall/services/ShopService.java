package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Dd01Mapper;
import cn.dyaoming.privatelife.wechatmall.mappers.Dd02Mapper;
import cn.dyaoming.privatelife.wechatmall.mappers.Pt01Mapper;
import cn.dyaoming.privatelife.wechatmall.mappers.Sp01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.*;
import com.github.pagehelper.PageHelper;
import com.google.protobuf.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
	public DataResult sendOrder(String openId, String name,String phoneNum,String address,String remark,String ydsj,String[] goodsList){
		DataResult dataResult = new DataResult();
		try{
			if (checkSession(openId)) {
				name = getDecryptParam(openId, name);
				phoneNum = getDecryptParam(openId, phoneNum);
				address = getDecryptParam(openId, address);
				remark = getDecryptParam(openId, remark);
				ydsj = getDecryptParam(openId, ydsj);
			} else {
				return new DataResult(false, "9011");
			}
			//获取用户编号


			//效验商品参数


			//填充订单内容
			Dd01 dd01 = new Dd01();
			dd01.setDda001(dd01Mapper.autoKey());
			dd01.setDda002();


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
				openId = getDecryptParam(openId, openId);
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
				openId = getDecryptParam(openId, openId);
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
}
