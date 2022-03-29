package com.yzp.spring.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ProjectName springbootsamples2_3_6
 * @Author yaozhenpeng
 * @Time 2022/3/27 22:19
 */
public class Student {
    @Id // 必须指定id列
    private String studentId;
    private String studentName;
    private Integer studentAge;
    private Double studentScore;
    private Date studentBirthday;

    public String getStudentId() {
        return studentId;
    }

    public Student setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public Student setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public Student setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
        return this;
    }

    public Double getStudentScore() {
        return studentScore;
    }

    public Student setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
        return this;
    }

    public Date getStudentBirthday() {
        return studentBirthday;
    }

    public Student setStudentBirthday(Date studentBirthday) {
        this.studentBirthday = studentBirthday;
        return this;
    }
}
