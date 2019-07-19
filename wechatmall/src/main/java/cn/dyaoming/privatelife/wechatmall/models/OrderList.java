package cn.dyaoming.privatelife.wechatmall.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class OrderList implements Serializable {

    private String orderId;
    private String orderType;
    private String state;
    private Timestamp time;
    private List<Map> children = new ArrayList<Map>();
    private int count;
    private BigDecimal totle;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<Map> getChildren() {
        return children;
    }

    public void setChildren(List<Map> children) {
        this.children = children;
        this.count = 0;
        children.stream().forEach(f->{
            count+=isNull(f.get("amount"))?(Integer) f.get("amount"):0;
        });
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getTotle() {
        return totle;
    }

    public void setTotle(BigDecimal totle) {
        this.totle = totle;
    }
}
