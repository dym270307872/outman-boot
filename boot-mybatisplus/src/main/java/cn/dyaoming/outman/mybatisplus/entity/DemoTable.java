package cn.dyaoming.outman.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import cn.dyaoming.outman.common.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 测试表结构生产
 * </p>
 *
 * @author dyaoming
 * @since 2020-03-23
 */
public class DemoTable extends BaseEntity<DemoTable> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    private String info;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "DemoTable{" +
            "Id=" + Id +
            ", info=" + info +
        "}";
    }
}
