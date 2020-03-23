package cn.dyaoming.outman.mybatisplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import cn.dyaoming.outman.baseEntity;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyaoming
 * @since 2020-03-23
 */
public class CmsProInfo extends baseEntity<CmsProInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    private String proNo;

    /**
     * 产品名称
     */
    private String proName;

    /**
     * 产品所属类型ID
     */
    private String proTypeId;

    /**
     * 产品描述1
     */
    private String proOrgNo;

    /**
     * 产品描述2
     */
    private String proDesc1;

    /**
     * 产品链接
     */
    private String proDesc2;

    /**
     * 是否锁定
     */
    private String isLock;

    private String proUrl;

    private String proUpDate;

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }
    public String getProOrgNo() {
        return proOrgNo;
    }

    public void setProOrgNo(String proOrgNo) {
        this.proOrgNo = proOrgNo;
    }
    public String getProDesc1() {
        return proDesc1;
    }

    public void setProDesc1(String proDesc1) {
        this.proDesc1 = proDesc1;
    }
    public String getProDesc2() {
        return proDesc2;
    }

    public void setProDesc2(String proDesc2) {
        this.proDesc2 = proDesc2;
    }
    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }
    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }
    public String getProUpDate() {
        return proUpDate;
    }

    public void setProUpDate(String proUpDate) {
        this.proUpDate = proUpDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.proNo;
    }

    @Override
    public String toString() {
        return "CmsProInfo{" +
            "proNo=" + proNo +
            ", proName=" + proName +
            ", proTypeId=" + proTypeId +
            ", proOrgNo=" + proOrgNo +
            ", proDesc1=" + proDesc1 +
            ", proDesc2=" + proDesc2 +
            ", isLock=" + isLock +
            ", proUrl=" + proUrl +
            ", proUpDate=" + proUpDate +
        "}";
    }
}
