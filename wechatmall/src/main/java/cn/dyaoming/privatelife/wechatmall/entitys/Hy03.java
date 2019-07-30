package cn.dyaoming.privatelife.wechatmall.entitys;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
public class Hy03  implements Serializable {
	private String hyc001;
	private String hyc002;
	private String hyc003;
	private String hyc004;
	private String hyc005;
	private String hyc006;
	private String hyc007;
	private String hyc008;
	private String hyc009;
	private String hyc010;
	private String hyc011;
	private String hyc012;
	private String hyc013;
	private String hyc014;
	private String hyc015;
	private String hyc016;
	private String hyc017;
	private Timestamp hyc018;
	private String hyc019;
	private String hyc020;



	@Id
	@Column(name = "hyc001", nullable = false, length = 20)
	public String getHyc001() {
		return hyc001;
	}



	public void setHyc001(String hyc001) {
		this.hyc001 = hyc001;
	}



	@Basic
	@Column(name = "hyc002", nullable = false, length = 20)
	public String getHyc002() {
		return hyc002;
	}



	public void setHyc002(String hyc002) {
		this.hyc002 = hyc002;
	}



	@Basic
	@Column(name = "hyc003", nullable = true, length = 1)
	public String getHyc003() {
		return hyc003;
	}



	public void setHyc003(String hyc003) {
		this.hyc003 = hyc003;
	}



	@Basic
	@Column(name = "hyc004", nullable = true, length = 1)
	public String getHyc004() {
		return hyc004;
	}



	public void setHyc004(String hyc004) {
		this.hyc004 = hyc004;
	}



	@Basic
	@Column(name = "hyc005", nullable = false, length = 3)
	public String getHyc005() {
		return hyc005;
	}



	public void setHyc005(String hyc005) {
		this.hyc005 = hyc005;
	}



	@Basic
	@Column(name = "hyc006", nullable = true, length = 1)
	public String getHyc006() {
		return hyc006;
	}



	public void setHyc006(String hyc006) {
		this.hyc006 = hyc006;
	}



	@Basic
	@Column(name = "hyc007", nullable = true, length = 50)
	public String getHyc007() {
		return hyc007;
	}



	public void setHyc007(String hyc007) {
		this.hyc007 = hyc007;
	}



	@Basic
	@Column(name = "hyc008", nullable = true, length = 50)
	public String getHyc008() {
		return hyc008;
	}



	public void setHyc008(String hyc008) {
		this.hyc008 = hyc008;
	}



	@Basic
	@Column(name = "hyc009", nullable = true, length = 200)
	public String getHyc009() {
		return hyc009;
	}



	public void setHyc009(String hyc009) {
		this.hyc009 = hyc009;
	}



	@Basic
	@Column(name = "hyc010", nullable = true, length = 1)
	public String getHyc010() {
		return hyc010;
	}



	public void setHyc010(String hyc010) {
		this.hyc010 = hyc010;
	}



	@Basic
	@Column(name = "hyc011", nullable = true, length = 1)
	public String getHyc011() {
		return hyc011;
	}



	public void setHyc011(String hyc011) {
		this.hyc011 = hyc011;
	}



	@Basic
	@Column(name = "hyc012", nullable = true, length = 1)
	public String getHyc012() {
		return hyc012;
	}



	public void setHyc012(String hyc012) {
		this.hyc012 = hyc012;
	}



	@Basic
	@Column(name = "hyc013", nullable = true, length = 1)
	public String getHyc013() {
		return hyc013;
	}



	public void setHyc013(String hyc013) {
		this.hyc013 = hyc013;
	}



	@Basic
	@Column(name = "hyc014", nullable = true, length = 1)
	public String getHyc014() {
		return hyc014;
	}



	public void setHyc014(String hyc014) {
		this.hyc014 = hyc014;
	}



	@Basic
	@Column(name = "hyc015", nullable = true, length = 1)
	public String getHyc015() {
		return hyc015;
	}



	public void setHyc015(String hyc015) {
		this.hyc015 = hyc015;
	}



	@Basic
	@Column(name = "hyc016", nullable = false, length = 3)
	public String getHyc016() {
		return hyc016;
	}



	public void setHyc016(String hyc016) {
		this.hyc016 = hyc016;
	}



	@Basic
	@Column(name = "hyc017", nullable = true, length = 20)
	public String getHyc017() {
		return hyc017;
	}



	public void setHyc017(String hyc017) {
		this.hyc017 = hyc017;
	}



	@Basic
	@Column(name = "hyc018", nullable = true)
	public Timestamp getHyc018() {
		return hyc018;
	}



	public void setHyc018(Timestamp hyc018) {
		this.hyc018 = hyc018;
	}



	@Basic
	@Column(name = "hyc019", nullable = true, length = 20)
	public String getHyc019() {
		return hyc019;
	}



	public void setHyc019(String hyc019) {
		this.hyc019 = hyc019;
	}



	@Basic
	@Column(name = "hyc020", nullable = true, length = 20)
	public String getHyc020() {
		return hyc020;
	}



	public void setHyc020(String hyc020) {
		this.hyc020 = hyc020;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Hy03 hy03 = (Hy03) o;

		if (hyc001 != null ? !hyc001.equals(hy03.hyc001) : hy03.hyc001 != null)
			return false;
		if (hyc002 != null ? !hyc002.equals(hy03.hyc002) : hy03.hyc002 != null)
			return false;
		if (hyc003 != null ? !hyc003.equals(hy03.hyc003) : hy03.hyc003 != null)
			return false;
		if (hyc004 != null ? !hyc004.equals(hy03.hyc004) : hy03.hyc004 != null)
			return false;
		if (hyc005 != null ? !hyc005.equals(hy03.hyc005) : hy03.hyc005 != null)
			return false;
		if (hyc006 != null ? !hyc006.equals(hy03.hyc006) : hy03.hyc006 != null)
			return false;
		if (hyc007 != null ? !hyc007.equals(hy03.hyc007) : hy03.hyc007 != null)
			return false;
		if (hyc008 != null ? !hyc008.equals(hy03.hyc008) : hy03.hyc008 != null)
			return false;
		if (hyc009 != null ? !hyc009.equals(hy03.hyc009) : hy03.hyc009 != null)
			return false;
		if (hyc010 != null ? !hyc010.equals(hy03.hyc010) : hy03.hyc010 != null)
			return false;
		if (hyc011 != null ? !hyc011.equals(hy03.hyc011) : hy03.hyc011 != null)
			return false;
		if (hyc012 != null ? !hyc012.equals(hy03.hyc012) : hy03.hyc012 != null)
			return false;
		if (hyc013 != null ? !hyc013.equals(hy03.hyc013) : hy03.hyc013 != null)
			return false;
		if (hyc014 != null ? !hyc014.equals(hy03.hyc014) : hy03.hyc014 != null)
			return false;
		if (hyc015 != null ? !hyc015.equals(hy03.hyc015) : hy03.hyc015 != null)
			return false;
		if (hyc016 != null ? !hyc016.equals(hy03.hyc016) : hy03.hyc016 != null)
			return false;
		if (hyc017 != null ? !hyc017.equals(hy03.hyc017) : hy03.hyc017 != null)
			return false;
		if (hyc018 != null ? !hyc018.equals(hy03.hyc018) : hy03.hyc018 != null)
			return false;
		if (hyc019 != null ? !hyc019.equals(hy03.hyc019) : hy03.hyc019 != null)
			return false;
		if (hyc020 != null ? !hyc020.equals(hy03.hyc020) : hy03.hyc020 != null)
			return false;

		return true;
	}



	@Override
	public int hashCode() {
		int result = hyc001 != null ? hyc001.hashCode() : 0;
		result = 31 * result + (hyc002 != null ? hyc002.hashCode() : 0);
		result = 31 * result + (hyc003 != null ? hyc003.hashCode() : 0);
		result = 31 * result + (hyc004 != null ? hyc004.hashCode() : 0);
		result = 31 * result + (hyc005 != null ? hyc005.hashCode() : 0);
		result = 31 * result + (hyc006 != null ? hyc006.hashCode() : 0);
		result = 31 * result + (hyc007 != null ? hyc007.hashCode() : 0);
		result = 31 * result + (hyc008 != null ? hyc008.hashCode() : 0);
		result = 31 * result + (hyc009 != null ? hyc009.hashCode() : 0);
		result = 31 * result + (hyc010 != null ? hyc010.hashCode() : 0);
		result = 31 * result + (hyc011 != null ? hyc011.hashCode() : 0);
		result = 31 * result + (hyc012 != null ? hyc012.hashCode() : 0);
		result = 31 * result + (hyc013 != null ? hyc013.hashCode() : 0);
		result = 31 * result + (hyc014 != null ? hyc014.hashCode() : 0);
		result = 31 * result + (hyc015 != null ? hyc015.hashCode() : 0);
		result = 31 * result + (hyc016 != null ? hyc016.hashCode() : 0);
		result = 31 * result + (hyc017 != null ? hyc017.hashCode() : 0);
		result = 31 * result + (hyc018 != null ? hyc018.hashCode() : 0);
		result = 31 * result + (hyc019 != null ? hyc019.hashCode() : 0);
		result = 31 * result + (hyc020 != null ? hyc020.hashCode() : 0);
		return result;
	}
}
