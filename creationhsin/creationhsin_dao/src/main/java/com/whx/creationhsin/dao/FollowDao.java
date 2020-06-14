package com.whx.creationhsin.dao;

import com.whx.creationhsin.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDao {

    /**
     * 关注作者
     */
    @Insert("insert into followuser (uid,otherUserId,status)value(#{uid},#{otherUserId},'已关注')")
    void addFollowUser(Follow follow) throws Exception;

    /**
     * 查找状态
     */
    @Select("select * from followuser where uid = #{uid} and otherUserId = #{otherUserId}")
    public Follow findFollowUserStatus(@Param("uid")String uid, @Param("otherUserId")String otherUserId) throws Exception;

    /**
     * 取消关注
     */
    @Delete("delete from followuser where uid = #{uid} and otherUserId = #{otherUserId}")
    void updateFollowStatus(@Param("uid")String uid, @Param("otherUserId")String otherUserId) throws Exception;

    /**
     * 收藏文章
     */

    @Insert("insert into followarticle (uid,articleId,followarticleId,starstatus)value(#{uid},#{articleId},#{followarticleId},'1')")
    void addFollowArticle(FollowArticle followArticle) throws Exception;

    /**
     * 根据用户id查找出收藏文章
     */
    @Select("select * from followarticle where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "followarticleId", property = "followarticleId"),
            @Result(column = "starstatus", property = "starstatus"),
            @Result(column = "articleId", property = "article", javaType = Article.class, one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById"))
    })
    List<FollowArticle> findFollowArticle(String uid) throws Exception;

    /**
     * 根据用户id和文章id判断是否收藏文章
     */
    @Select("select * from followarticle where uid=#{uid} and articleId=#{articleId}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "followarticleId", property = "followarticleId"),
            @Result(column = "starstatus", property = "starstatus"),
            @Result(column = "articleId", property = "article", javaType = Article.class, one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById"))
    })
    public FollowArticle findFollowArticleById(@Param("uid")String uid, @Param("articleId")String articleId) throws Exception;

    /**
     * 取消收藏文章
     */
    @Delete("delete from followarticle where uid = #{uid} and articleId = #{articleId}")
    void deleteFollowArticleStatus(@Param("uid")String uid, @Param("articleId")String articleId) throws Exception;

    /**
     * 关注专题
     */
    @Insert("insert into followthematic(uid,thematicId,thm_status)value(#{uid},#{thematicId},'已关注')")
    void addFollowThematic(FollowThematic followThematic) throws Exception;

    /**
     * 取消关注
     */
    @Delete("delete from followthematic where uid = #{uid} and thematicId = #{thematicId}")
    void updateFollow(@Param("uid")String uid, @Param("thematicId")String thematicId) throws Exception;

    /**
     * 查找状态
     */
    @Select("select * from followthematic where uid = #{uid} and thematicId = #{thematicId}")
    public FollowThematic findFollowThematicStatus(@Param("uid")String uid, @Param("thematicId")String thematicId) throws Exception;

    /**
     * 查询此专题中有哪些用户关注了，显示头像
     */
    @Select("select * from followthematic where thematicId=#{thematicId}")
    @Results({
            @Result(id = true, property = "thematicId", column = "thematicId"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById"))
    })
    List<FollowThematic> findFollowThematicOfUser(String thematicId) throws Exception;

    /**
     * 查询此专题中有哪些用户关注了，显示头像
     */
    @Select("select * from followuser where otherUserId=#{otherUserId}")
    @Results({
            @Result(id = true, property = "otherUserId", column = "otherUserId"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById"))
    })
    List<Follow> findFollowUserOfOther(String otherUserId) throws Exception;

    /**
     * 查询用户关注了哪些人并显示出来
     */
    @Select("select * from followuser where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "otherUserId", property = "otherUserId"),
            @Result(column = "otherUserId", property = "user", javaType = User.class, one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserByOtherUId"))
    })
    List<Follow> findFollowUserListOfPersonalFollow(String uid) throws Exception;

    /**
     * 查询用户关注了哪些专题并显示出来
     */
    @Select("select * from followthematic where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "thematicId", property = "thematicId"),
            @Result(column = "thematicId", property = "thematic", javaType = Thematic.class, one = @One(select = "com.whx.creationhsin.dao.ThematicDao.findThematicById"))
    })
    List<FollowThematic> findFollowThematicListOfPersonalFollow(String uid) throws Exception;

}
