package com.whx.creationhsin.dao;

import com.whx.creationhsin.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleDao {


    /**
     * 查询所有文章-普通用户
     * @return
     * @throws Exception
     */
    @Select("select *from article where articlestate = '网审合格'")
    List<Article> findAll2() throws Exception;

    @Select("select *from article where articlestate != 0")
    List<Article> findByPage2() throws Exception;

    /**
     * 查询所有文章-管理员
     * @return
     * @throws Exception
     */
    @Select("select *from article")
    List<Article> findAll() throws Exception;

    @Select("select *from article")
    List<Article> findByPage() throws Exception;


    /**
     * 根据articleid查询文章
     * 涉及多表查询
     * @param articleId
     * @return
     * @throws Exception
     */
    @Select("select * from article where articleid=#{articleid}")
    @Results({
            @Result(id = true, property = "articleId", column = "articleid"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "head", property = "head"),
            @Result(column = "author",property = "author"),
            @Result(column = "articlestate",property = "articlestate"),
            @Result(column = "like",property = "like"),
            @Result(column = "time",property = "time"),
            @Result(column = "content",property = "content"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById"))
             })
    public Article findArticleById(String articleId) throws Exception;

    /**
     * 添加文章
     * @param article
     * @throws Exception
     */
    @Insert("insert into article(articleId,uid,head,author,articlestate,content,`like`,time)value(#{articleId},#{uid},#{head},#{author},'等待网审',#{content},'0',#{time})")
    void addArticle(Article article) throws Exception;

    /**
     * 点赞数增加
     * @param article
     * @throws Exception
     */
    @Update("update article set `like` = #{like}  where articleid = #{articleId}")
    void updateArticleLike(Article article) throws Exception;

    /**
     * 点赞数减少
     */
    @Update("update article set `like` = #{like}  where articleid = #{articleId}")
    void updateArticleLikeSub(Article article) throws Exception;

    /**
     * 点赞
     */
    @Insert("insert into `like`(uid,articleId,likebyuser)value(#{uid},#{articleId},'1')")
    void addArticleLike(Like like) throws Exception;

    /**
     * 取消点赞
     */
    @Delete("delete from `like` where uid = #{uid} and articleId = #{articleId}")
    void deleteLikeArticleStatus(@Param("uid")String uid, @Param("articleId")String articleId) throws Exception;

    /**
     * 根据用户id和文章id查找点赞状态，禁止用户重复点赞
     */
    @Select("select * from `like` where uid=#{uid} and articleId=#{articleId}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "likebyuser", property = "likebyuser"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
            @Result(column = "articleId", property = "article", javaType = Article.class, one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById"))
    })
    public Like findArticleLikeById(@Param("uid")String uid, @Param("articleId")String articleId) throws Exception;

    /**
     * 修改文章信息
     * @param article
     * @throws Exception
     */
    @Update("update article set articlestate = #{articlestate} where articleid = #{articleId}")
    void updateArticleMessage(Article article) throws Exception;

    /**
     * 删除文章
     * @param articleId
     * @throws Exception
     */
    @Delete("delete from article where articleid = #{articleId}")
    void deleteArticleById(String articleId) throws Exception;


    /**
     * 根据用户寻找用户自己的文章
     * @param uid
     * @return
     * @throws Exception
     */
    @Select("select * from article where uid=#{uid} and articlestate ='网审合格'")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "head", property = "head"),
            @Result(column = "author",property = "author"),
            @Result(column = "articlestate",property = "articlestate"),
            @Result(column = "like",property = "like"),
            @Result(column = "time",property = "time"),
            @Result(column = "content",property = "content"),
           @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
  })
    List<Article> findArticleByUId(String uid) throws Exception;


    /**
     * 根据用户寻找用户自己的文章-所有文章，包括未过审文章
     * @param uid
     * @return
     * @throws Exception
     */
    @Select("select * from article where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "head", property = "head"),
            @Result(column = "author",property = "author"),
            @Result(column = "articlestate",property = "articlestate"),
            @Result(column = "like",property = "like"),
            @Result(column = "time",property = "time"),
            @Result(column = "content",property = "content"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById")),
    })
    List<Article> findArticleByUIdAll(String uid) throws Exception;

}
