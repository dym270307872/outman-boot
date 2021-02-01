package cn.dyaoming.outman.entity;


import java.io.Serializable;


public class TempDO implements Serializable {

    private String code;
    private String msg;



    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }

}
