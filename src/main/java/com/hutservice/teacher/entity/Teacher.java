package com.hutservice.teacher.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hutservice.application.config.AppConfig;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;

/**
 * 教师实体类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Entity
public class Teacher {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = AppConfig.UUID_GENERATOR)
    @GeneratedValue(generator = "idGenerator")
    private String teacherID;

    //工号
    private String teaNo;

    //密码
    private String teaPassword;

    //姓名
    private String teaName;

    //身份号
    private String teaIdCard;

    //手机号
    private String teaPhoneNo;

    //居住地
    private String teaAdress;

    //民族
    private String teaNation;

    //年龄
    private String teaAge;

    //出生日期
    private String teaBirthday;

    //创建日期
    @DateTimeFormat(pattern = AppConfig.DATE_FORMAT)
    @JsonFormat(pattern = AppConfig.DATE_FORMAT, timezone = AppConfig.TIMEZONE)
    private Date createDate;

    public Teacher() {
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeaNo() {
        return teaNo;
    }

    public void setTeaNo(String teaNo) {
        this.teaNo = teaNo;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaIdCard() {
        return teaIdCard;
    }

    public void setTeaIdCard(String teaIdCard) {
        this.teaIdCard = teaIdCard;
    }

    public String getTeaPhoneNo() {
        return teaPhoneNo;
    }

    public void setTeaPhoneNo(String teaPhoneNo) {
        this.teaPhoneNo = teaPhoneNo;
    }

    public String getTeaAdress() {
        return teaAdress;
    }

    public void setTeaAdress(String teaAdress) {
        this.teaAdress = teaAdress;
    }

    public String getTeaNation() {
        return teaNation;
    }

    public void setTeaNation(String teaNation) {
        this.teaNation = teaNation;
    }

    public String getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(String teaAge) {
        this.teaAge = teaAge;
    }

    public String getTeaBirthday() {
        return teaBirthday;
    }

    public void setTeaBirthday(String teaBirthday) {
        this.teaBirthday = teaBirthday;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
