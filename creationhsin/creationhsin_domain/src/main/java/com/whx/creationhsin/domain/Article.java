package com.whx.creationhsin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "handler",ignoreUnknown = true)
public class Article {
    private String articleId;
    private String uid;
    private String head;
    private String author;
    private int like;
    private String articlestate;
    private String content;
    private String time;

    private int likeNum=0;
   // private int likeNumSub;
    private int N = 1;

    private User user;


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getArticlestate() {
        return articlestate;
    }

    public void setArticlestate(String articlestate) {
        this.articlestate = articlestate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public static void main(String[] args){
        Article article = new Article();
        for(int i =0;i<10;i++){
            article.setLikeNum(article.getLikeNum()-1);
//        article.setLikeNum();
            article.setLike(article.getLikeNum());
            int m = article.getLike();
            System.out.println("m="+m);
        }

    }
}
