package cn.dyaoming.privatelife.wechatmall.models;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
public class Acb02 implements Serializable {
	private String hya001;
	private String acbb001;
	private String acbb002;
	private String acbb003;
	private String isvalid;
	private Timestamp createtime;
	private String creater;
	private String creation;



	@Basic
	@Column(name = "hya001", nullable = true, length = 20)
	public String getHya001() {
		return hya001;
	}



	public void setHya001(String hya001) {
		this.hya001 = hya001;
	}



	@Id
	@Column(name = "acbb001", nullable = true, length = 20)
	public String getAcbb001() {
		return acbb001;
	}



	public void setAcbb001(String acbb001) {
		this.acbb001 = acbb001;
	}



	@Basic
	@Column(name = "acbb002", nullable = true, length = 20)
	public String getAcbb002() {
		return acbb002;
	}



	public void setAcbb002(String acbb002) {
		this.acbb002 = acbb002;
	}



	@Basic
	@Column(name = "acbb003", nullable = true, length = 20)
	public String getAcbb003() {
		return acbb003;
	}



	public void setAcbb003(String acbb003) {
		this.acbb003 = acbb003;
	}



	@Basic
	@Column(name = "isvalid", nullable = true, length = 1)
	public String getIsvalid() {
		return isvalid;
	}



	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}



	@Basic
	@Column(name = "createtime", nullable = true)
	public Timestamp getCreatetime() {
		return createtime;
	}



	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}



	@Basic
	@Column(name = "creater", nullable = true, length = 20)
	public String getCreater() {
		return creater;
	}



	public void setCreater(String creater) {
		this.creater = creater;
	}



	@Basic
	@Column(name = "creation", nullable = true, length = 20)
	public String getCreation() {
		return creation;
	}



	public void setCreation(String creation) {
		this.creation = creation;
	}



}
