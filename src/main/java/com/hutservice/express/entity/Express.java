package com.hutservice.express.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hutservice.application.config.AppConfig;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 快递实体类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Entity
public class Express {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = AppConfig.UUID_GENERATOR)
    @GeneratedValue(generator = "idGenerator")
    private String expressID;

    //快递公司
    private String type;

    //快递单号
    private String number;

    //时间
    private String time;

    //状态
    private String status;

    //物流状态 1在途中 2派件中 3已签收 4派送失败(拒签等)
    private Integer deliveryStatus;

    //创建日期
    @DateTimeFormat(pattern = AppConfig.DATE_FORMAT)
    @JsonFormat(pattern = AppConfig.DATE_FORMAT, timezone = AppConfig.TIMEZONE)
    private Date createDate;

    public Express() {
    }

    public String getExpressID() {
        return expressID;
    }

    public void setExpressID(String expressID) {
        this.expressID = expressID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
