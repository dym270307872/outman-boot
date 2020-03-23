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
public class CmsProType extends baseEntity<CmsProType> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品类型ID
     */
    private String proTypeId;

    /**
     * 产品类型名称
     */
    private String proTypeName;

    /**
     * 排序
     */
    private String sn;

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }
    public String getProTypeName() {
        return proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    protected Serializable pkVal() {
        return this.proTypeId;
    }

    @Override
    public String toString() {
        return "CmsProType{" +
            "proTypeId=" + proTypeId +
            ", proTypeName=" + proTypeName +
            ", sn=" + sn +
        "}";
    }
}
