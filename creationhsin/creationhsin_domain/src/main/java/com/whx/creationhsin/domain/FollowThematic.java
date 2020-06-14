package com.whx.creationhsin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "handler",ignoreUnknown = true)
public class FollowThematic {
    private String uid;
    private String thematicId;
    private String thm_status;

    private User user;
    private Thematic thematic;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getThematicId() {
        return thematicId;
    }

    public void setThematicId(String thematicId) {
        this.thematicId = thematicId;
    }

    public String getThm_status() {
        return thm_status;
    }

    public void setThm_status(String thm_status) {
        this.thm_status = thm_status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thematic getThematic() {
        return thematic;
    }

    public void setThematic(Thematic thematic) {
        this.thematic = thematic;
    }
}
