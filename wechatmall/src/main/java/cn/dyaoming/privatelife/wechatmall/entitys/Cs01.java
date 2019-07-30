package cn.dyaoming.privatelife.wechatmall.entitys;

import cn.dyaoming.privatelife.wechatmall.models.CsInfo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Cs01 {
    private String csa001;
    private String csa002;
    private String csa003;
    private String csa004;
    private String csa005;
    private String csa006;
    private String csa007;
    private String csa008;
    private String csa009;
    private String csa010;
    private String csa011;
    private String csa012;
    private String csa013;
    private String csa014;
    private String csa015;
    private String csa016;
    private String csa017;
    private Date csa018;
    private String csa019;
    private String csa020;

    @Id
    @Column(name = "csa001", nullable = false, length = 20)
    public String getCsa001() {
        return csa001;
    }

    public void setCsa001(String csa001) {
        this.csa001 = csa001;
    }

    @Basic
    @Column(name = "csa002", nullable = true, length = 20)
    public String getCsa002() {
        return csa002;
    }

    public void setCsa002(String csa002) {
        this.csa002 = csa002;
    }

    @Basic
    @Column(name = "csa003", nullable = true, length = 3)
    public String getCsa003() {
        return csa003;
    }

    public void setCsa003(String csa003) {
        this.csa003 = csa003;
    }

    @Basic
    @Column(name = "csa004", nullable = true, length = 3)
    public String getCsa004() {
        return csa004;
    }

    public void setCsa004(String csa004) {
        this.csa004 = csa004;
    }

    @Basic
    @Column(name = "csa005", nullable = true, length = 1)
    public String getCsa005() {
        return csa005;
    }

    public void setCsa005(String csa005) {
        this.csa005 = csa005;
    }

    @Basic
    @Column(name = "csa006", nullable = true, length = 20)
    public String getCsa006() {
        return csa006;
    }

    public void setCsa006(String csa006) {
        this.csa006 = csa006;
    }

    @Basic
    @Column(name = "csa007", nullable = true, length = 20)
    public String getCsa007() {
        return csa007;
    }

    public void setCsa007(String csa007) {
        this.csa007 = csa007;
    }

    @Basic
    @Column(name = "csa008", nullable = true, length = 50)
    public String getCsa008() {
        return csa008;
    }

    public void setCsa008(String csa008) {
        this.csa008 = csa008;
    }

    @Basic
    @Column(name = "csa009", nullable = true, length = 1)
    public String getCsa009() {
        return csa009;
    }

    public void setCsa009(String csa009) {
        this.csa009 = csa009;
    }

    @Basic
    @Column(name = "csa010", nullable = true, length = 50)
    public String getCsa010() {
        return csa010;
    }

    public void setCsa010(String csa010) {
        this.csa010 = csa010;
    }

    @Basic
    @Column(name = "csa011", nullable = true, length = 200)
    public String getCsa011() {
        return csa011;
    }

    public void setCsa011(String csa011) {
        this.csa011 = csa011;
    }

    @Basic
    @Column(name = "csa012", nullable = true, length = 1)
    public String getCsa012() {
        return csa012;
    }

    public void setCsa012(String csa012) {
        this.csa012 = csa012;
    }

    @Basic
    @Column(name = "csa013", nullable = true, length = 20)
    public String getCsa013() {
        return csa013;
    }

    public void setCsa013(String csa013) {
        this.csa013 = csa013;
    }

    @Basic
    @Column(name = "csa014", nullable = true, length = 1)
    public String getCsa014() {
        return csa014;
    }

    public void setCsa014(String csa014) {
        this.csa014 = csa014;
    }

    @Basic
    @Column(name = "csa015", nullable = true, length = 1)
    public String getCsa015() {
        return csa015;
    }

    public void setCsa015(String csa015) {
        this.csa015 = csa015;
    }

    @Basic
    @Column(name = "csa016", nullable = true, length = 1)
    public String getCsa016() {
        return csa016;
    }

    public void setCsa016(String csa016) {
        this.csa016 = csa016;
    }

    @Basic
    @Column(name = "csa017", nullable = false, length = 3)
    public String getCsa017() {
        return csa017;
    }

    public void setCsa017(String csa017) {
        this.csa017 = csa017;
    }

    @Basic
    @Column(name = "csa018", nullable = true)
    public Date getCsa018() {
        return csa018;
    }

    public void setCsa018(Date csa018) {
        this.csa018 = csa018;
    }

    @Basic
    @Column(name = "csa019", nullable = true, length = 20)
    public String getCsa019() {
        return csa019;
    }

    public void setCsa019(String csa019) {
        this.csa019 = csa019;
    }

    @Basic
    @Column(name = "csa020", nullable = true, length = 20)
    public String getCsa020() {
        return csa020;
    }

    public void setCsa020(String csa020) {
        this.csa020 = csa020;
    }

    public CsInfo toInfo(){
        CsInfo csInfo = new CsInfo();
        csInfo.setId(getCsa001());
        csInfo.setCode(getCsa007());
        csInfo.setValue(getCsa008());
        csInfo.setPid(getCsa013());
        return csInfo;
    }
}
