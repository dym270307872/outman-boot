package cn.dyaoming.privatelife.wechatmall.models;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
public class Dd02  implements Serializable {
	private String ddb001;
	private String ddb002;
	private String ddb003;
	private String ddb004;
	private String ddb005;
	private String ddb006;
	private String ddb007;
	private BigDecimal ddb008;
	private BigDecimal ddb009;
	private String ddb010;
	private String ddb011;
	private String ddb012;
	private String ddb013;
	private String ddb014;
	private String ddb015;
	private String ddb016;
	private String ddb017;
	private Timestamp ddb018;
	private String ddb019;
	private String ddb020;



	@Id
	@Column(name = "ddb001", nullable = false, length = 20)
	public String getDdb001() {
		return ddb001;
	}



	public void setDdb001(String ddb001) {
		this.ddb001 = ddb001;
	}



	@Basic
	@Column(name = "ddb002", nullable = false, length = 20)
	public String getDdb002() {
		return ddb002;
	}



	public void setDdb002(String ddb002) {
		this.ddb002 = ddb002;
	}



	@Basic
	@Column(name = "ddb003", nullable = true, length = 1)
	public String getDdb003() {
		return ddb003;
	}



	public void setDdb003(String ddb003) {
		this.ddb003 = ddb003;
	}



	@Basic
	@Column(name = "ddb004", nullable = true, length = 20)
	public String getDdb004() {
		return ddb004;
	}



	public void setDdb004(String ddb004) {
		this.ddb004 = ddb004;
	}



	@Basic
	@Column(name = "ddb005", nullable = false, length = 50)
	public String getDdb005() {
		return ddb005;
	}



	public void setDdb005(String ddb005) {
		this.ddb005 = ddb005;
	}



	@Basic
	@Column(name = "ddb006", nullable = true, length = 200)
	public String getDdb006() {
		return ddb006;
	}



	public void setDdb006(String ddb006) {
		this.ddb006 = ddb006;
	}



	@Basic
	@Column(name = "ddb007", nullable = true, length = 200)
	public String getDdb007() {
		return ddb007;
	}



	public void setDdb007(String ddb007) {
		this.ddb007 = ddb007;
	}



	@Basic
	@Column(name = "ddb008", nullable = true, precision = 2)
	public BigDecimal getDdb008() {
		return ddb008;
	}



	public void setDdb008(BigDecimal ddb008) {
		this.ddb008 = ddb008;
	}



	@Basic
	@Column(name = "ddb009", nullable = true, precision = 2)
	public BigDecimal getDdb009() {
		return ddb009;
	}



	public void setDdb009(BigDecimal ddb009) {
		this.ddb009 = ddb009;
	}



	@Basic
	@Column(name = "ddb010", nullable = true, length = 6)
	public String getDdb010() {
		return ddb010;
	}



	public void setDdb010(String ddb010) {
		this.ddb010 = ddb010;
	}



	@Basic
	@Column(name = "ddb011", nullable = true, length = 200)
	public String getDdb011() {
		return ddb011;
	}



	public void setDdb011(String ddb011) {
		this.ddb011 = ddb011;
	}



	@Basic
	@Column(name = "ddb012", nullable = true, length = 3)
	public String getDdb012() {
		return ddb012;
	}



	public void setDdb012(String ddb012) {
		this.ddb012 = ddb012;
	}



	@Basic
	@Column(name = "ddb013", nullable = true, length = 1)
	public String getDdb013() {
		return ddb013;
	}



	public void setDdb013(String ddb013) {
		this.ddb013 = ddb013;
	}



	@Basic
	@Column(name = "ddb014", nullable = true, length = 1)
	public String getDdb014() {
		return ddb014;
	}



	public void setDdb014(String ddb014) {
		this.ddb014 = ddb014;
	}



	@Basic
	@Column(name = "ddb015", nullable = true, length = 1)
	public String getDdb015() {
		return ddb015;
	}



	public void setDdb015(String ddb015) {
		this.ddb015 = ddb015;
	}



	@Basic
	@Column(name = "ddb016", nullable = false, length = 3)
	public String getDdb016() {
		return ddb016;
	}



	public void setDdb016(String ddb016) {
		this.ddb016 = ddb016;
	}



	@Basic
	@Column(name = "ddb017", nullable = true, length = 20)
	public String getDdb017() {
		return ddb017;
	}



	public void setDdb017(String ddb017) {
		this.ddb017 = ddb017;
	}



	@Basic
	@Column(name = "ddb018", nullable = false)
	public Timestamp getDdb018() {
		return ddb018;
	}



	public void setDdb018(Timestamp ddb018) {
		this.ddb018 = ddb018;
	}



	@Basic
	@Column(name = "ddb019", nullable = true, length = 20)
	public String getDdb019() {
		return ddb019;
	}



	public void setDdb019(String ddb019) {
		this.ddb019 = ddb019;
	}



	@Basic
	@Column(name = "ddb020", nullable = true, length = 20)
	public String getDdb020() {
		return ddb020;
	}



	public void setDdb020(String ddb020) {
		this.ddb020 = ddb020;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Dd02 dd02 = (Dd02) o;

		if (ddb001 != null ? !ddb001.equals(dd02.ddb001) : dd02.ddb001 != null)
			return false;
		if (ddb002 != null ? !ddb002.equals(dd02.ddb002) : dd02.ddb002 != null)
			return false;
		if (ddb003 != null ? !ddb003.equals(dd02.ddb003) : dd02.ddb003 != null)
			return false;
		if (ddb004 != null ? !ddb004.equals(dd02.ddb004) : dd02.ddb004 != null)
			return false;
		if (ddb005 != null ? !ddb005.equals(dd02.ddb005) : dd02.ddb005 != null)
			return false;
		if (ddb006 != null ? !ddb006.equals(dd02.ddb006) : dd02.ddb006 != null)
			return false;
		if (ddb007 != null ? !ddb007.equals(dd02.ddb007) : dd02.ddb007 != null)
			return false;
		if (ddb008 != null ? !ddb008.equals(dd02.ddb008) : dd02.ddb008 != null)
			return false;
		if (ddb009 != null ? !ddb009.equals(dd02.ddb009) : dd02.ddb009 != null)
			return false;
		if (ddb010 != null ? !ddb010.equals(dd02.ddb010) : dd02.ddb010 != null)
			return false;
		if (ddb011 != null ? !ddb011.equals(dd02.ddb011) : dd02.ddb011 != null)
			return false;
		if (ddb012 != null ? !ddb012.equals(dd02.ddb012) : dd02.ddb012 != null)
			return false;
		if (ddb013 != null ? !ddb013.equals(dd02.ddb013) : dd02.ddb013 != null)
			return false;
		if (ddb014 != null ? !ddb014.equals(dd02.ddb014) : dd02.ddb014 != null)
			return false;
		if (ddb015 != null ? !ddb015.equals(dd02.ddb015) : dd02.ddb015 != null)
			return false;
		if (ddb016 != null ? !ddb016.equals(dd02.ddb016) : dd02.ddb016 != null)
			return false;
		if (ddb017 != null ? !ddb017.equals(dd02.ddb017) : dd02.ddb017 != null)
			return false;
		if (ddb018 != null ? !ddb018.equals(dd02.ddb018) : dd02.ddb018 != null)
			return false;
		if (ddb019 != null ? !ddb019.equals(dd02.ddb019) : dd02.ddb019 != null)
			return false;
		if (ddb020 != null ? !ddb020.equals(dd02.ddb020) : dd02.ddb020 != null)
			return false;

		return true;
	}



	@Override
	public int hashCode() {
		int result = ddb001 != null ? ddb001.hashCode() : 0;
		result = 31 * result + (ddb002 != null ? ddb002.hashCode() : 0);
		result = 31 * result + (ddb003 != null ? ddb003.hashCode() : 0);
		result = 31 * result + (ddb004 != null ? ddb004.hashCode() : 0);
		result = 31 * result + (ddb005 != null ? ddb005.hashCode() : 0);
		result = 31 * result + (ddb006 != null ? ddb006.hashCode() : 0);
		result = 31 * result + (ddb007 != null ? ddb007.hashCode() : 0);
		result = 31 * result + (ddb008 != null ? ddb008.hashCode() : 0);
		result = 31 * result + (ddb009 != null ? ddb009.hashCode() : 0);
		result = 31 * result + (ddb010 != null ? ddb010.hashCode() : 0);
		result = 31 * result + (ddb011 != null ? ddb011.hashCode() : 0);
		result = 31 * result + (ddb012 != null ? ddb012.hashCode() : 0);
		result = 31 * result + (ddb013 != null ? ddb013.hashCode() : 0);
		result = 31 * result + (ddb014 != null ? ddb014.hashCode() : 0);
		result = 31 * result + (ddb015 != null ? ddb015.hashCode() : 0);
		result = 31 * result + (ddb016 != null ? ddb016.hashCode() : 0);
		result = 31 * result + (ddb017 != null ? ddb017.hashCode() : 0);
		result = 31 * result + (ddb018 != null ? ddb018.hashCode() : 0);
		result = 31 * result + (ddb019 != null ? ddb019.hashCode() : 0);
		result = 31 * result + (ddb020 != null ? ddb020.hashCode() : 0);
		return result;
	}
}
