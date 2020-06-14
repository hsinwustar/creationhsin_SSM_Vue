package com.whx.creationhsin.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "handler",ignoreUnknown = true)
public class ThematicArticle {

    private String thematicId;
    private String articleId;
    private String thm_art_status;

    private Article article;
    private Thematic thematic;

    public String getThematicId() {
        return thematicId;
    }

    public void setThematicId(String thematicId) {
        this.thematicId = thematicId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Thematic getThematic() {
        return thematic;
    }

    public void setThematic(Thematic thematic) {
        this.thematic = thematic;
    }

    public String getThm_art_status() {
        return thm_art_status;
    }

    public void setThm_art_status(String thm_art_status) {
        this.thm_art_status = thm_art_status;
    }
}
