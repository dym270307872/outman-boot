package cn.dyaoming.log.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "test_log", schema = "redis", catalog = "")
public class TestLog {
    private int id;
    private String skey;
    private Long slength;
    private Date beginTime;
    private Date endTime;
    private String dataFrom;
private Long time;
    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "skey", nullable = true, length = 255)
    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    @Basic
    @Column(name = "slength", nullable = true)
    public Long getSlength() {
        return slength;
    }

    public void setSlength(Long slength) {
        this.slength = slength;
    }

    @Basic
    @Column(name = "begin_time", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)    //获取数据库时间格式
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")    //@ResponseBody返回的时间格式
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)    //获取数据库时间格式
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")    //@ResponseBody返回的时间格式
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "data_from", nullable = true, length = 255)
    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }
    @Basic
    @Column(name = "time", nullable = true)
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
