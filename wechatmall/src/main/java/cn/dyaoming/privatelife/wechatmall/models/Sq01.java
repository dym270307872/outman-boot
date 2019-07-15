package cn.dyaoming.privatelife.wechatmall.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Sq01  implements Serializable {
    private String sqa001;
    private String sqa002;
    private String sqa005;
    private String sqa006;
    private String sqa007;
    private String isvalid;
    private Timestamp createtime;
    private String creater;
    private String creation;

    @Id
    @Column(name = "sqa001", nullable = true, length = 20)
    public String getSqa001() {
        return sqa001;
    }

    public void setSqa001(String sqa001) {
        this.sqa001 = sqa001;
    }

    @Basic
    @Column(name = "sqa002", nullable = true, length = 20)
    public String getSqa002() {
        return sqa002;
    }

    public void setSqa002(String sqa002) {
        this.sqa002 = sqa002;
    }

    @Basic
    @Column(name = "sqa005", nullable = true, length = 200)
    public String getSqa005() {
        return sqa005;
    }

    public void setSqa005(String sqa005) {
        this.sqa005 = sqa005;
    }

    @Basic
    @Column(name = "sqa006", nullable = true, length = 200)
    public String getSqa006() {
        return sqa006;
    }

    public void setSqa006(String sqa006) {
        this.sqa006 = sqa006;
    }

    @Basic
    @Column(name = "sqa007", nullable = true, length = 200)
    public String getSqa007() {
        return sqa007;
    }

    public void setSqa007(String sqa007) {
        this.sqa007 = sqa007;
    }

    @Basic
    @Column(name = "isvalid", nullable = true, length = 1)
    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    @Basic
    @Column(name = "createtime", nullable = true)
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "creater", nullable = true, length = 20)
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Basic
    @Column(name = "creation", nullable = true, length = 20)
    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

}
