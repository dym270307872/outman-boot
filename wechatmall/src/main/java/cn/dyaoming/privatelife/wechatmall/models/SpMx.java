package cn.dyaoming.privatelife.wechatmall.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpMx implements Serializable {

    private String goodsId;
    private String type;
    private String goodsName;
    private List<String> goodsImages = new ArrayList<String>();
    private String goodsInfo;
    private String description;
    private String instruction;
    private String gg;
    private BigDecimal dj;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<String> getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(List<String> goodsImages) {
        this.goodsImages = goodsImages;
    }

    public void addGoodsImages(String goodsImage) {
        this.goodsImages.add(goodsImage);
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public BigDecimal getDj() {
        return dj;
    }

    public void setDj(BigDecimal dj) {
        this.dj = dj;
    }



    public String getInstruction() {
        return instruction;
    }



    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
