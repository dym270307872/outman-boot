package cn.dyaoming.privatelife.wechatmall.models;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
public class Hy06  implements Serializable {
	private String hyf001;
	private String hyf002;
	private String hyf003;
	private String hyf004;
	private BigDecimal hyf005;
	private String hyf006;
	private String hyf007;
	private String hyf008;
	private String hyf009;
	private String hyf010;
	private BigDecimal hyf011;
	private BigDecimal hyf012;
	private BigDecimal hyf013;
	private BigDecimal hyf014;
	private BigDecimal hyf015;
	private Long hyf016;
	private String hyf017;
	private Timestamp hyf018;
	private String hyf019;
	private String hyf020;



	@Id
	@Column(name = "HYF001", nullable = false, length = 20)
	public String getHyf001() {
		return hyf001;
	}



	public void setHyf001(String hyf001) {
		this.hyf001 = hyf001;
	}



	@Basic
	@Column(name = "HYF002", nullable = true, length = 20)
	public String getHyf002() {
		return hyf002;
	}



	public void setHyf002(String hyf002) {
		this.hyf002 = hyf002;
	}



	@Basic
	@Column(name = "HYF003", nullable = true, length = 20)
	public String getHyf003() {
		return hyf003;
	}



	public void setHyf003(String hyf003) {
		this.hyf003 = hyf003;
	}



	@Basic
	@Column(name = "HYF004", nullable = true, length = 20)
	public String getHyf004() {
		return hyf004;
	}



	public void setHyf004(String hyf004) {
		this.hyf004 = hyf004;
	}



	@Basic
	@Column(name = "HYF005", nullable = true, precision = 2)
	public BigDecimal getHyf005() {
		return hyf005;
	}



	public void setHyf005(BigDecimal hyf005) {
		this.hyf005 = hyf005;
	}



	@Basic
	@Column(name = "HYF006", nullable = true, length = 20)
	public String getHyf006() {
		return hyf006;
	}



	public void setHyf006(String hyf006) {
		this.hyf006 = hyf006;
	}



	@Basic
	@Column(name = "HYF007", nullable = true, length = 20)
	public String getHyf007() {
		return hyf007;
	}



	public void setHyf007(String hyf007) {
		this.hyf007 = hyf007;
	}



	@Basic
	@Column(name = "HYF008", nullable = true, length = 20)
	public String getHyf008() {
		return hyf008;
	}



	public void setHyf008(String hyf008) {
		this.hyf008 = hyf008;
	}



	@Basic
	@Column(name = "HYF009", nullable = true, length = 32)
	public String getHyf009() {
		return hyf009;
	}



	public void setHyf009(String hyf009) {
		this.hyf009 = hyf009;
	}



	@Basic
	@Column(name = "HYF010", nullable = true, length = 200)
	public String getHyf010() {
		return hyf010;
	}



	public void setHyf010(String hyf010) {
		this.hyf010 = hyf010;
	}



	@Basic
	@Column(name = "HYF011", nullable = false, precision = 2)
	public BigDecimal getHyf011() {
		return hyf011;
	}



	public void setHyf011(BigDecimal hyf011) {
		this.hyf011 = hyf011;
	}



	@Basic
	@Column(name = "HYF012", nullable = true, precision = 2)
	public BigDecimal getHyf012() {
		return hyf012;
	}



	public void setHyf012(BigDecimal hyf012) {
		this.hyf012 = hyf012;
	}



	@Basic
	@Column(name = "HYF013", nullable = true, precision = 2)
	public BigDecimal getHyf013() {
		return hyf013;
	}



	public void setHyf013(BigDecimal hyf013) {
		this.hyf013 = hyf013;
	}



	@Basic
	@Column(name = "HYF014", nullable = true, precision = 2)
	public BigDecimal getHyf014() {
		return hyf014;
	}



	public void setHyf014(BigDecimal hyf014) {
		this.hyf014 = hyf014;
	}



	@Basic
	@Column(name = "HYF015", nullable = true, precision = 2)
	public BigDecimal getHyf015() {
		return hyf015;
	}



	public void setHyf015(BigDecimal hyf015) {
		this.hyf015 = hyf015;
	}



	@Basic
	@Column(name = "HYF016", nullable = true)
	public Long getHyf016() {
		return hyf016;
	}



	public void setHyf016(Long hyf016) {
		this.hyf016 = hyf016;
	}



	@Basic
	@Column(name = "HYF017", nullable = true, length = 3)
	public String getHyf017() {
		return hyf017;
	}



	public void setHyf017(String hyf017) {
		this.hyf017 = hyf017;
	}



	@Basic
	@Column(name = "HYF018", nullable = true)
	public Timestamp getHyf018() {
		return hyf018;
	}



	public void setHyf018(Timestamp hyf018) {
		this.hyf018 = hyf018;
	}



	@Basic
	@Column(name = "HYF019", nullable = true, length = 20)
	public String getHyf019() {
		return hyf019;
	}



	public void setHyf019(String hyf019) {
		this.hyf019 = hyf019;
	}



	@Basic
	@Column(name = "HYF020", nullable = true, length = 20)
	public String getHyf020() {
		return hyf020;
	}



	public void setHyf020(String hyf020) {
		this.hyf020 = hyf020;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Hy06 hy06 = (Hy06) o;

		if (hyf001 != null ? !hyf001.equals(hy06.hyf001) : hy06.hyf001 != null)
			return false;
		if (hyf002 != null ? !hyf002.equals(hy06.hyf002) : hy06.hyf002 != null)
			return false;
		if (hyf003 != null ? !hyf003.equals(hy06.hyf003) : hy06.hyf003 != null)
			return false;
		if (hyf004 != null ? !hyf004.equals(hy06.hyf004) : hy06.hyf004 != null)
			return false;
		if (hyf005 != null ? !hyf005.equals(hy06.hyf005) : hy06.hyf005 != null)
			return false;
		if (hyf006 != null ? !hyf006.equals(hy06.hyf006) : hy06.hyf006 != null)
			return false;
		if (hyf007 != null ? !hyf007.equals(hy06.hyf007) : hy06.hyf007 != null)
			return false;
		if (hyf008 != null ? !hyf008.equals(hy06.hyf008) : hy06.hyf008 != null)
			return false;
		if (hyf009 != null ? !hyf009.equals(hy06.hyf009) : hy06.hyf009 != null)
			return false;
		if (hyf010 != null ? !hyf010.equals(hy06.hyf010) : hy06.hyf010 != null)
			return false;
		if (hyf011 != null ? !hyf011.equals(hy06.hyf011) : hy06.hyf011 != null)
			return false;
		if (hyf012 != null ? !hyf012.equals(hy06.hyf012) : hy06.hyf012 != null)
			return false;
		if (hyf013 != null ? !hyf013.equals(hy06.hyf013) : hy06.hyf013 != null)
			return false;
		if (hyf014 != null ? !hyf014.equals(hy06.hyf014) : hy06.hyf014 != null)
			return false;
		if (hyf015 != null ? !hyf015.equals(hy06.hyf015) : hy06.hyf015 != null)
			return false;
		if (hyf016 != null ? !hyf016.equals(hy06.hyf016) : hy06.hyf016 != null)
			return false;
		if (hyf017 != null ? !hyf017.equals(hy06.hyf017) : hy06.hyf017 != null)
			return false;
		if (hyf018 != null ? !hyf018.equals(hy06.hyf018) : hy06.hyf018 != null)
			return false;
		if (hyf019 != null ? !hyf019.equals(hy06.hyf019) : hy06.hyf019 != null)
			return false;
		if (hyf020 != null ? !hyf020.equals(hy06.hyf020) : hy06.hyf020 != null)
			return false;

		return true;
	}



	@Override
	public int hashCode() {
		int result = hyf001 != null ? hyf001.hashCode() : 0;
		result = 31 * result + (hyf002 != null ? hyf002.hashCode() : 0);
		result = 31 * result + (hyf003 != null ? hyf003.hashCode() : 0);
		result = 31 * result + (hyf004 != null ? hyf004.hashCode() : 0);
		result = 31 * result + (hyf005 != null ? hyf005.hashCode() : 0);
		result = 31 * result + (hyf006 != null ? hyf006.hashCode() : 0);
		result = 31 * result + (hyf007 != null ? hyf007.hashCode() : 0);
		result = 31 * result + (hyf008 != null ? hyf008.hashCode() : 0);
		result = 31 * result + (hyf009 != null ? hyf009.hashCode() : 0);
		result = 31 * result + (hyf010 != null ? hyf010.hashCode() : 0);
		result = 31 * result + (hyf011 != null ? hyf011.hashCode() : 0);
		result = 31 * result + (hyf012 != null ? hyf012.hashCode() : 0);
		result = 31 * result + (hyf013 != null ? hyf013.hashCode() : 0);
		result = 31 * result + (hyf014 != null ? hyf014.hashCode() : 0);
		result = 31 * result + (hyf015 != null ? hyf015.hashCode() : 0);
		result = 31 * result + (hyf016 != null ? hyf016.hashCode() : 0);
		result = 31 * result + (hyf017 != null ? hyf017.hashCode() : 0);
		result = 31 * result + (hyf018 != null ? hyf018.hashCode() : 0);
		result = 31 * result + (hyf019 != null ? hyf019.hashCode() : 0);
		result = 31 * result + (hyf020 != null ? hyf020.hashCode() : 0);
		return result;
	}
}
