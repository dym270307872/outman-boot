package cn.dyaoming.outman.mybatisplus.entity;

import java.io.Serializable;

import cn.dyaoming.outman.common.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyaoming
 * @since 2020-03-23
 */
public class CmsOrgInfo extends BaseEntity<CmsOrgInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构编号
     */
    private String orgNo;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构级别
     */
    private String orgLevel;

    /**
     * 省行机构编号
     */
    private String proOrgNo;

    /**
     * 机构地址
     */
    private String orgSite;

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }
    public String getProOrgNo() {
        return proOrgNo;
    }

    public void setProOrgNo(String proOrgNo) {
        this.proOrgNo = proOrgNo;
    }
    public String getOrgSite() {
        return orgSite;
    }

    public void setOrgSite(String orgSite) {
        this.orgSite = orgSite;
    }

    @Override
    protected Serializable pkVal() {
        return this.orgNo;
    }

    @Override
    public String toString() {
        return "CmsOrgInfo{" +
            "orgNo=" + orgNo +
            ", orgName=" + orgName +
            ", orgLevel=" + orgLevel +
            ", proOrgNo=" + proOrgNo +
            ", orgSite=" + orgSite +
        "}";
    }
}
