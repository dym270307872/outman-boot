package cn.dyaoming.outman.mybatisplus.entity;

import cn.dyaoming.outman.common.BaseEntity;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyaoming
 * @since 2020-03-23
 */
public class CmsManageInfo extends BaseEntity<CmsManageInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    private String empNo;

    /**
     * 员工名称
     */
    private String empName;

    /**
     * 员工所属机构号
     */
    private String empOrgNo;

    /**
     * 员工ehr号
     */
    private String empEhrCode;

    /**
     * 电话号
     */
    private String tel;

    /**
     * 手机号
     */
    private String mobel;

    /**
     * 微信号
     */
    private String weChatNo;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getEmpOrgNo() {
        return empOrgNo;
    }

    public void setEmpOrgNo(String empOrgNo) {
        this.empOrgNo = empOrgNo;
    }
    public String getEmpEhrCode() {
        return empEhrCode;
    }

    public void setEmpEhrCode(String empEhrCode) {
        this.empEhrCode = empEhrCode;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getMobel() {
        return mobel;
    }

    public void setMobel(String mobel) {
        this.mobel = mobel;
    }
    public String getWeChatNo() {
        return weChatNo;
    }

    public void setWeChatNo(String weChatNo) {
        this.weChatNo = weChatNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.empNo;
    }

    @Override
    public String toString() {
        return "CmsManageInfo{" +
            "empNo=" + empNo +
            ", empName=" + empName +
            ", empOrgNo=" + empOrgNo +
            ", empEhrCode=" + empEhrCode +
            ", tel=" + tel +
            ", mobel=" + mobel +
            ", weChatNo=" + weChatNo +
        "}";
    }
}
