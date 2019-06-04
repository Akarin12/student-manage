package com.sm.entity;

import java.util.Date;

public class RoPVO {
    private Integer id;
    private String studentId;
    private String studentName;
    private String details;
    private Integer rOrP;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getrOrP() {
        return rOrP;
    }

    public void setrOrP(Integer rOrP) {
        this.rOrP = rOrP;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RoPVO{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", details='" + details + '\'' +
                ", rOrP=" + rOrP +
                ", date=" + date +
                '}';
    }
}
