package com.whx.creationhsin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonIgnoreProperties(value = "handler",ignoreUnknown = true)
public class Thematic {
    private String thematicId;
    private String uid;
    private String thm_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thm_day;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date thm_time;
    private String thm_check;
    private String thm_submit;
    private String thm_describe;
    private String thm_icon;


    private User user;


    public String getThematicId() {
        return thematicId;
    }

    public void setThematicId(String thematicId) {
        this.thematicId = thematicId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getThm_name() {
        return thm_name;
    }

    public void setThm_name(String thm_name) {
        this.thm_name = thm_name;
    }

    public Date getThm_day() {
        return thm_day;
    }

    public void setThm_day(Date thm_day) {
        this.thm_day = thm_day;
    }

    public Date getThm_time() {
        return thm_time;
    }

    public void setThm_time(Date thm_time) {
        this.thm_time = thm_time;
    }

    public String getThm_check() {
        return thm_check;
    }

    public void setThm_check(String thm_check) {
        this.thm_check = thm_check;
    }

    public String getThm_submit() {
        return thm_submit;
    }

    public void setThm_submit(String thm_submit) {
        this.thm_submit = thm_submit;
    }

    public String getThm_describe() {
        return thm_describe;
    }

    public void setThm_describe(String thm_describe) {
        this.thm_describe = thm_describe;
    }

    public String getThm_icon() {
        return thm_icon;
    }

    public void setThm_icon(String thm_icon) {
        this.thm_icon = thm_icon;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
