package com.whx.creationhsin.service;

import com.github.pagehelper.PageInfo;
import com.whx.creationhsin.domain.Article;
import com.whx.creationhsin.domain.Like;
import com.whx.creationhsin.domain.PageBean;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface ArticleService {

    /**
     * 查询所有文章
     * @return
     * @throws Exception
     */
    public List<Article>findAll()throws Exception;

    /**
     * 查询所有文章
     * @return
     * @throws Exception
     */
    public List<Article>findAll2()throws Exception;

    /**
     * 分页查询
     * @param pageCode
     * @param pageSize
     * @return
     * @throws Exception
     */
   public PageBean findByPage(int pageCode,int pageSize) throws Exception;


    /**
     * 根据articleid查询文章
     * @param articleId
     * @return
     * @throws Exception
     */
    public Article findArticleById(String articleId) throws Exception;

    /**
     * 添加文章
     * @param article
     * @throws Exception
     */

    void addArticle(Article article) throws Exception;

    /**
     * 点赞数增加
     * @param article
     * @throws Exception
     */
    void updateArticleLike(Article article) throws Exception;

 /**
  * 点赞数减少
  * @param article
  * @throws Exception
  */
    void updateArticleLikeSub(Article article) throws Exception;


    /**
     * 点赞
     * @param like
     * @throws Exception
     */
    void addArticleLike(Like like)throws Exception;

    /**
     * 取消点赞
     */
    void deleteLikeArticleStatus(String uid,String articleId)throws Exception;

    /**
     * 根据用户id和文章id判断是否点赞，禁止用户重复点赞
     */
    public Like findArticleLikeById(String uid,String articleId) throws Exception;

    /**
     * 修改图书信息
     * @param article
     * @throws Exception
     */
    void updateArticleMessage(Article article)throws Exception;

    /**
     * 删除文章
     * @param articleId
     * @throws Exception
     */
    void deleteArticleById(String articleId) throws Exception;

/*    *//**
     * 管理员更改文章状态，使之校验通过
     * @param
     * @return
     * @throws Exception
     *//*
    public void updateArticleState(Article article)throws Exception;*/

    /**
     * 根据用户查找自己文章
     * @param uid
     * @return
     * @throws Exception
     */
    public List<Article> findArticleByUId(String uid) throws Exception;

 /**
  * 根据用户查找自己文章
  * @param uid
  * @return
  * @throws Exception
  */
 public List<Article> findArticleByUIdAll(String uid) throws Exception;

}
