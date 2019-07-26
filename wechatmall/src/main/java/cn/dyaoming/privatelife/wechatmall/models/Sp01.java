package cn.dyaoming.privatelife.wechatmall.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Sp01 {
    private String spa001;
    private String spa002;
    private String spa003;
    private String spa004;
    private String spa005;
    private String spa006;
    private String spa007;
    private BigDecimal spa008;
    private String spa009;
    private String spa010;
    private String spa011;
    private String spa012;
    private String spa013;
    private String spa014;
    private String spa015;
    private String spa016;
    private String spa017;
    private Timestamp spa018;
    private String spa019;
    private String spa020;

    @Basic
    @Column(name = "spa001", nullable = false, length = 20)
    public String getSpa001() {
        return spa001;
    }

    public void setSpa001(String spa001) {
        this.spa001 = spa001;
    }

    @Basic
    @Column(name = "spa002", nullable = true, length = 20)
    public String getSpa002() {
        return spa002;
    }

    public void setSpa002(String spa002) {
        this.spa002 = spa002;
    }

    @Basic
    @Column(name = "spa003", nullable = true, length = 20)
    public String getSpa003() {
        return spa003;
    }

    public void setSpa003(String spa003) {
        this.spa003 = spa003;
    }

    @Basic
    @Column(name = "spa004", nullable = true, length = 50)
    public String getSpa004() {
        return spa004;
    }

    public void setSpa004(String spa004) {
        this.spa004 = spa004;
    }

    @Basic
    @Column(name = "spa005", nullable = false, length = 50)
    public String getSpa005() {
        return spa005;
    }

    public void setSpa005(String spa005) {
        this.spa005 = spa005;
    }

    @Basic
    @Column(name = "spa006", nullable = true, length = 200)
    public String getSpa006() {
        return spa006;
    }

    public void setSpa006(String spa006) {
        this.spa006 = spa006;
    }

    @Basic
    @Column(name = "spa007", nullable = true, length = 200)
    public String getSpa007() {
        return spa007;
    }

    public void setSpa007(String spa007) {
        this.spa007 = spa007;
    }

    @Basic
    @Column(name = "spa008", nullable = true, precision = 2)
    public BigDecimal getSpa008() {
        return spa008;
    }

    public void setSpa008(BigDecimal spa008) {
        this.spa008 = spa008;
    }

    @Basic
    @Column(name = "spa009", nullable = true, length = 6)
    public String getSpa009() {
        return spa009;
    }

    public void setSpa009(String spa009) {
        this.spa009 = spa009;
    }

    @Basic
    @Column(name = "spa010", nullable = true, length = 1)
    public String getSpa010() {
        return spa010;
    }

    public void setSpa010(String spa010) {
        this.spa010 = spa010;
    }

    @Basic
    @Column(name = "spa011", nullable = true, length = 3)
    public String getSpa011() {
        return spa011;
    }

    public void setSpa011(String spa011) {
        this.spa011 = spa011;
    }

    @Basic
    @Column(name = "spa012", nullable = true, length = 3)
    public String getSpa012() {
        return spa012;
    }

    public void setSpa012(String spa012) {
        this.spa012 = spa012;
    }

    @Basic
    @Column(name = "spa013", nullable = true, length = 1)
    public String getSpa013() {
        return spa013;
    }

    public void setSpa013(String spa013) {
        this.spa013 = spa013;
    }

    @Basic
    @Column(name = "spa014", nullable = true, length = 1)
    public String getSpa014() {
        return spa014;
    }

    public void setSpa014(String spa014) {
        this.spa014 = spa014;
    }

    @Basic
    @Column(name = "spa015", nullable = true, length = 1)
    public String getSpa015() {
        return spa015;
    }

    public void setSpa015(String spa015) {
        this.spa015 = spa015;
    }

    @Basic
    @Column(name = "spa016", nullable = false, length = 3)
    public String getSpa016() {
        return spa016;
    }

    public void setSpa016(String spa016) {
        this.spa016 = spa016;
    }

    @Basic
    @Column(name = "spa017", nullable = true, length = 1)
    public String getSpa017() {
        return spa017;
    }

    public void setSpa017(String spa017) {
        this.spa017 = spa017;
    }

    @Basic
    @Column(name = "spa018", nullable = false)
    public Timestamp getSpa018() {
        return spa018;
    }

    public void setSpa018(Timestamp spa018) {
        this.spa018 = spa018;
    }

    @Basic
    @Column(name = "spa019", nullable = true, length = 20)
    public String getSpa019() {
        return spa019;
    }

    public void setSpa019(String spa019) {
        this.spa019 = spa019;
    }

    @Basic
    @Column(name = "spa020", nullable = true, length = 20)
    public String getSpa020() {
        return spa020;
    }

    public void setSpa020(String spa020) {
        this.spa020 = spa020;
    }

    public SpInfo toInfo(){
        SpInfo spInfo = new SpInfo();
        spInfo.setTypeId(getSpa003());
        spInfo.setGoodsId(getSpa001());
        spInfo.setGoodsImage(getSpa004());
        spInfo.setGoodsName(getSpa005());
        spInfo.setDj(getSpa008());
        spInfo.setGg(getSpa009());
        return spInfo;
    }

    public SpMx toMx(){
        SpMx spMx = new SpMx();
        spMx.setGoodsId(getSpa001());
        spMx.setType(getSpa003());
        spMx.addGoodsImages(getSpa004());
        spMx.setGoodsName(getSpa005());
        spMx.setGoodsInfo(getSpa006());
        spMx.setDescription(getSpa007());
        spMx.setGg(getSpa009());
        spMx.setDj(getSpa008());
        return spMx;
    }
}
