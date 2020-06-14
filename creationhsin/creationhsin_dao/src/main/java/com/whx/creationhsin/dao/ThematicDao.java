package com.whx.creationhsin.dao;

import com.whx.creationhsin.domain.Article;
import com.whx.creationhsin.domain.Thematic;
import com.whx.creationhsin.domain.ThematicArticle;
import com.whx.creationhsin.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThematicDao {

    /**
     * 添加专题
     * @param thematic
     * @throws Exception
     */
    @Insert("insert into thematic(thematicid,thm_icon,thm_name,uid,thm_day,thm_time,thm_check,thm_submit,thm_describe)value(#{thematicId},#{thm_icon},#{thm_name},#{uid},#{thm_day},#{thm_time},#{thm_check},#{thm_submit},#{thm_describe})")
    void AddThematic(Thematic thematic) throws Exception;


    /**
     * 根据专题id查询专题
     * @param thematicId
     * @return
     * @throws Exception
     */
    @Select("select * from thematic where thematicid=#{thematicid}")
    @Results({
            @Result(id = true, property = "thematicId", column = "thematicid"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "thm_icon", property = "thm_icon"),
            @Result(column = "thm_name",property = "thm_name"),
            @Result(column = "thm_day",property = "thm_day"),
            @Result(column = "thm_time",property = "thm_time"),
            @Result(column = "thm_check",property = "thm_check"),
            @Result(column = "thm_submit",property = "thm_submit"),
            @Result(column = "thm_describe",property = "thm_describe"),
            @Result(column = "uid", property = "user", javaType = User.class,one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById"))
    })
    public Thematic findThematicById(String thematicId) throws Exception;

    /***
     * 查找所有专题内容
     * @return
     * @throws Exception
     */
    @Select("select *from thematic")
    List<Thematic> findAll() throws Exception;

    /**
     * 收录文章入专题
     * @param thematicArticle
     * @throws Exception
     */
    @Insert("insert into thematicarticle (thematicId,articleId)value(#{thematicId},#{articleId})")
    void addArticleToThematic(ThematicArticle thematicArticle) throws Exception;

    /**
     * 取消收录文章入专题
     * @param articleId
     * @param thematicId
     * @throws Exception
     */
    @Delete("delete from thematicarticle where articleId = #{articleId} and thematicId = #{thematicId}")
    void updateArticleByThematic(@Param("articleId")String articleId, @Param("thematicId")String thematicId) throws Exception;

    /**
     * 查询收录状态
     * @param articleId
     * @param thematicId
     * @return
     * @throws Exception
     */
    @Select("select * from thematicarticle where articleId = #{articleId} and thematicId = #{thematicId}")
    @Results({
            @Result(id = true, property = "thematicId", column = "thematicid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "thm_art_status", property = "thm_art_status"),
            @Result(column = "articleId", property = "article", javaType = Article.class,one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById")),
            @Result(column = "thematicId", property = "thematic", javaType = Thematic.class,one = @One(select = "com.whx.creationhsin.dao.ThematicDao.findThematicById"))
    })
    public ThematicArticle findArticleToThematicStatus(@Param("articleId")String articleId, @Param("thematicId")String thematicId) throws Exception;

    /**
     * 查找出专题包含的所有文章
     * @return
     * @throws Exception
     */
    @Select("select * from thematicarticle where thematicid=#{thematicid}")
    @Results({
            @Result(id = true, property = "thematicId", column = "thematicid"),
            @Result(column = "articleId", property = "articleId"),
            @Result(column = "articleId", property = "article", javaType = Article.class,one = @One(select = "com.whx.creationhsin.dao.ArticleDao.findArticleById")),
            @Result(column = "thematicId", property = "thematic", javaType = Thematic.class,one = @One(select = "com.whx.creationhsin.dao.ThematicDao.findThematicById"))
    })
    List<ThematicArticle> findAllArticleInThematic(String thematicId) throws Exception;

    /**
     * 查询文章所属的所有专题
     * @param articleId
     * @return
     * @throws Exception
     */
    @Select("select * from thematicarticle where articleId=#{articleId}")
    @Results({
            @Result(id = true, property = "articleId", column = "articleId"),
            @Result(column = "thematicId", property = "thematicId"),
            @Result(column = "thematicId", property = "thematic", javaType = Thematic.class,one = @One(select = "com.whx.creationhsin.dao.ThematicDao.findThematicById"))
    })
    List<ThematicArticle> findAllThematicInArticle(String articleId) throws Exception;

    /**
     * 查询用户创建的专题
     */
    @Select("select * from thematic where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(column = "thematicId", property = "thematicId"),
            @Result(column = "thm_icon", property = "thm_icon"),
            @Result(column = "thm_name",property = "thm_name"),
            @Result(column = "thm_day",property = "thm_day"),
            @Result(column = "thm_time",property = "thm_time"),
            @Result(column = "thm_check",property = "thm_check"),
            @Result(column = "thm_submit",property = "thm_submit"),
            @Result(column = "thm_describe",property = "thm_describe"),
            @Result(column = "uid", property = "user", javaType = User.class,one = @One(select = "com.whx.creationhsin.dao.UserDao.findUserById"))
    })
    List<Thematic> findAllThematicInUser(String uid) throws Exception;

    /**
     * 删除专题
     */
    @Delete("delete from thematic where thematicId = #{thematicId}")
    void deleteThematic(@Param("thematicId")String thematicId) throws Exception;


    /**
     * 编辑专题资料
     */
    @Update("update thematic set thm_name = #{thm_name},thm_icon = #{thm_icon},thm_check = #{thm_check},thm_submit = #{thm_submit},thm_describe = #{thm_describe} where thematicId = #{thematicId}")
    void updateThematicModify(Thematic thematic) throws Exception;

}
