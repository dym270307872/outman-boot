package cn.dyaoming.privatelife.wechatmall.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;


public class OrderInfo implements Serializable {
	private String     orderId;//订单编号
	private String     orderType;//订单类型
	private String     state;//订单状态
	private Timestamp  time;//下单时间
	private List<Map>  children = new ArrayList<Map>();//商品列表
	private BigDecimal count;//商品总数量
	private BigDecimal totle;//金额总计

	//备注
	private String remarks;

	//    收货人
	private String    name;
	//    收货手机
	private String    phoneNum;
	//    收货地址
	private String    address;
	//配送时间
	private Timestamp deliveryTime;



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Timestamp getTime() {
		return time;
	}



	public void setTime(Timestamp time) {
		this.time = time;
	}



	public List<Map> getChildren() {
		return children;
	}



	public void setChildren(List<Map> children) {
		this.children = children;
		this.count = new BigDecimal("0.00");
		for(Map map : children) {
			this.count = this.count.add(isNull(map.get("amount")) ? new BigDecimal("0.00") : (BigDecimal)map.get("amount"));
		}
	}



	public BigDecimal getCount() {
		return count;
	}



	public BigDecimal getTotle() {
		return totle;
	}



	public void setTotle(BigDecimal totle) {
		this.totle = totle;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhoneNum() {
		return phoneNum;
	}



	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}



	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
}
