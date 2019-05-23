package cn.dyaoming.outman.entitys;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * <p>Temp实体类</p>
 * 
 * @author DYAOMING
 * @since 2019-04-26
 * @version V1.0
 */
@Entity
@Table(name = "temp", schema = "outman")
public class Temp implements Serializable {

	@Basic
	@Id
	@Column(name = "code", nullable = false, length = 20)
	private String	code;

	@Basic
	@Column(name = "msg", nullable = false, length = 20)
	private String	msg;



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}

}
