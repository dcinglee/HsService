package com.hutservice.student.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hutservice.application.config.AppConfig;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 学生实体类
 *
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
@Entity
public class Student {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = AppConfig.UUID_GENERATOR)
    @GeneratedValue(generator = "idGenerator")
    private String studentID;

    //学号
    private String stuNo;

    //密码
    private String stuPassword;

    //姓名
    private String stuName;

    //性别
    private String stuSex;

    //出生日期
    private String stuBirthday;

    //身份证号
    private String stuIdCard;

    //年龄
    private String stuAge;

    //民族
    private String stuNation;

    //居住地
    private String stuAdress;

    //手机号
    private String stuPhoneNo;

    //创建日期
    @DateTimeFormat(pattern = AppConfig.DATE_FORMAT)
    @JsonFormat(pattern = AppConfig.DATE_FORMAT, timezone = AppConfig.TIMEZONE)
    private Date createDate;

    public Student() {
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuIdCard() {
        return stuIdCard;
    }

    public void setStuIdCard(String stuIdCard) {
        this.stuIdCard = stuIdCard;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuNation() {
        return stuNation;
    }

    public void setStuNation(String stuNation) {
        this.stuNation = stuNation;
    }

    public String getStuAdress() {
        return stuAdress;
    }

    public void setStuAdress(String stuAdress) {
        this.stuAdress = stuAdress;
    }

    public String getStuPhoneNo() {
        return stuPhoneNo;
    }

    public void setStuPhoneNo(String stuPhoneNo) {
        this.stuPhoneNo = stuPhoneNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
