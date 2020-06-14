package com.whx.creationhsin.dao;

import com.whx.creationhsin.domain.Article;
import com.whx.creationhsin.domain.Comment;
import com.whx.creationhsin.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    /**
     * 根据评论id查询评论
     * 涉及多表查询
     * @param commentId
     * @return
     * @throws Exception
     */
    @Select("select * from comment where commentId=#{commentId}")
    @Results({
            @Result(id = true, property = "commentId", column = "commentId"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "content",property = "content"),
            @Result(column = "time",property = "time"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
            @Result(column = "articleId", property = "article", javaType = Article.class, one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById"))
    })
    List<Comment> findCommentById(String commentId) throws Exception;

    /**
     * 根据文章id评论
     */
    @Select("select * from comment where articleId=#{articleId}")
    @Results({
            @Result(id = true, property = "articleId", column = "articleId"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "commentId", property = "commentId"),
            @Result(column = "content",property = "content"),
            @Result(column = "time",property = "time"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
            @Result(column = "articleId", property = "article", javaType = Article.class, one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById"))
    })
    List<Comment> findCommentByarticleId(String articleId) throws Exception;

    /**
     * 根据用户id查询评论
     */
    @Select("select * from comment where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "commentId", property = "commentId"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "content",property = "content"),
            @Result(column = "time",property = "time"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
            @Result(column = "articleId", property = "article", javaType = Article.class, one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById"))
    })
    List<Comment> findCommentByUId(String uid) throws Exception;

    /**
     * 添加评论
     */

    @Insert("insert into comment(commentId,articleId,uid,content,time,username,usericon)value(#{commentId},#{articleId},#{uid},#{content},#{time},#{username},#{usericon})")
    void addComment(Comment comment) throws Exception;

    /**
     * 查找收到的所有评论消息
     */
    @Select(" select c.content,c.uid,c.time,c.username,c.usericon,a.head,a.articleId from article a,`user` u,`comment` c WHERE u.uid = #{uid} and u.uid = a.uid and a.articleid = c.articleid")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "commentId", property = "commentId"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "content",property = "content"),
            @Result(column = "useername",property = "useername"),
            @Result(column = "usericon",property = "usericon"),
            @Result(column = "time",property = "time"),
//            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
            @Result(column = "head",property = "head")
    })
    List<Comment> findCommentByUIdandArticleId(String uid) throws Exception;

}
