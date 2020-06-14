package com.whx.creationhsin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "handler",ignoreUnknown = true)
public class FollowArticle {
    private String uid;
    private String articleId;
    private String followarticleId;
    private String starstatus;

    private Article article;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getFollowarticleId() {
        return followarticleId;
    }

    public void setFollowarticleId(String followarticleId) {
        this.followarticleId = followarticleId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getStarstatus() {
        return starstatus;
    }

    public void setStarstatus(String starstatus) {
        this.starstatus = starstatus;
    }
}
