package cn.dyaoming.privatelife.wechatmall.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CsInfo implements Serializable {
    private String id;
    private String pid;

    private String code;
    private String value;
    private List<CsInfo> children = null;

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<CsInfo> getChildren() {
        return children;
    }

    public void setChildren(List<CsInfo> children) {
        this.children = children;
    }

    public void addChild(CsInfo csInfo){
        if(this.children == null){
            this.children = new ArrayList<CsInfo>();
        }
        this.children.add(csInfo);
    }
}
