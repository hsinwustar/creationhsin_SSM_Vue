package com.whx.creationhsin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "handler",ignoreUnknown = true)
public class User {
    private String uid; //用户id
    private String username;   //用户昵称
    private String userpwd; //用户密码
    private String usertype;//用户类型，管理员：0，用户：1
    private String useremail;
    private String useraddress;
    private String usericon;
    private String userstatus;
    private String userdescribe;
    private String otherUserId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public String getUserdescribe() {
        return userdescribe;
    }

    public void setUserdescribe(String userdescribe) {
        this.userdescribe = userdescribe;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String uid) {
        this.otherUserId = uid;
    }
}
