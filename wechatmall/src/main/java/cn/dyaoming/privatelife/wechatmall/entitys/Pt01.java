package cn.dyaoming.privatelife.wechatmall.entitys;

import cn.dyaoming.privatelife.wechatmall.models.PtInfo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Pt01 {
    private String pta001;
    private String pta002;
    private String pta003;
    private String pta004;
    private String pta005;
    private String pta006;
    private String pta007;
    private Timestamp pta008;
    private String pta009;
    private String pta010;

    @Basic
    @Column(name = "pta001", nullable = true, length = 20)
    public String getPta001() {
        return pta001;
    }

    public void setPta001(String pta001) {
        this.pta001 = pta001;
    }

    @Basic
    @Column(name = "pta002", nullable = true, length = 20)
    public String getPta002() {
        return pta002;
    }

    public void setPta002(String pta002) {
        this.pta002 = pta002;
    }

    @Basic
    @Column(name = "pta003", nullable = true, length = 50)
    public String getPta003() {
        return pta003;
    }

    public void setPta003(String pta003) {
        this.pta003 = pta003;
    }

    @Basic
    @Column(name = "pta004", nullable = true, length = 200)
    public String getPta004() {
        return pta004;
    }

    public void setPta004(String pta004) {
        this.pta004 = pta004;
    }

    @Basic
    @Column(name = "pta005", nullable = true, length = 200)
    public String getPta005() {
        return pta005;
    }

    public void setPta005(String pta005) {
        this.pta005 = pta005;
    }

    @Basic
    @Column(name = "pta006", nullable = true, length = 1)
    public String getPta006() {
        return pta006;
    }

    public void setPta006(String pta006) {
        this.pta006 = pta006;
    }

    @Basic
    @Column(name = "pta007", nullable = false, length = 3)
    public String getPta007() {
        return pta007;
    }

    public void setPta007(String pta007) {
        this.pta007 = pta007;
    }

    @Basic
    @Column(name = "pta008", nullable = true)
    public Timestamp getPta008() {
        return pta008;
    }

    public void setPta008(Timestamp pta008) {
        this.pta008 = pta008;
    }

    @Basic
    @Column(name = "pta009", nullable = true, length = 20)
    public String getPta009() {
        return pta009;
    }

    public void setPta009(String pta009) {
        this.pta009 = pta009;
    }

    @Basic
    @Column(name = "pta010", nullable = true, length = 20)
    public String getPta010() {
        return pta010;
    }

    public void setPta010(String pta010) {
        this.pta010 = pta010;
    }

   public PtInfo toInfo(){
        PtInfo ptInfo = new PtInfo();
        ptInfo.setTypeId(getPta001());
        ptInfo.setTypeName(getPta002());
        return ptInfo;
   }
}
