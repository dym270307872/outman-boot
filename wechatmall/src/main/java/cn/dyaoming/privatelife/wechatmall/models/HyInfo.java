package cn.dyaoming.privatelife.wechatmall.models;


import java.io.Serializable;
import java.math.BigDecimal;


public class HyInfo implements Serializable {
	private String     hyId;
	private String     hyCardId;
	private String     phoneNum;
	private String     hyName;
	private BigDecimal ye;



	public String getHyId() {
		return hyId;
	}



	public void setHyId(String hyId) {
		this.hyId = hyId;
	}



	public String getHyCardId() {
		return hyCardId;
	}



	public void setHyCardId(String hyCardId) {
		this.hyCardId = hyCardId;
	}



	public String getPhoneNum() {
		return phoneNum;
	}



	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



	public String getHyName() {
		return hyName;
	}



	public void setHyName(String hyName) {
		this.hyName = hyName;
	}



	public BigDecimal getYe() {
		return ye;
	}



	public void setYe(BigDecimal ye) {
		this.ye = ye;
	}
}
