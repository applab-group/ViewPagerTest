package com.aal.sekihan.viewpagertest;

/**
 * Created by sekihan on 2017/09/16.
 */

public class SyllabusListItem {
    private String code = null;
    private String quarter = null;
    private String day = null;
    private String subNumber = null;
    private String teacher = null;
    private String address = null;
    private String name = null;


    public String getCode() {
        return code;
    }

    public String getQuarter() {
        return quarter;
    }

    public String getDay() {
        return day;
    }

    public String getSubNumber() {
        return subNumber;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getAddress() {
        return address;
    }

    public String getName(){
        return name;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubNumber(String subNumber) {
        this.subNumber = subNumber;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
