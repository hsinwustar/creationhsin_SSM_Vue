package com.whx.creationhsin.service;

import com.whx.creationhsin.domain.Thematic;
import com.whx.creationhsin.domain.ThematicArticle;

import java.util.List;

public interface ThematicService {

    /**
     * 添加标签
     * @param thematic
     * @throws Exception
     */
    void AddThematic(Thematic thematic) throws Exception;

    /**
     * 根据专题id查询专题
     * @param thematicId
     * @return
     * @throws Exception
     */
    public Thematic findThematicById(String thematicId) throws Exception;

    /**
     * 查找所有专题
     * @return
     * @throws Exception
     */
    public List<Thematic> findAll()throws Exception;

    /**
     * 收录文章入专题
     * @param thematicArticle
     * @throws Exception
     */
    void addArticleToThematic(ThematicArticle thematicArticle) throws Exception;

    /**
     * 取消关注
     */
    void updateArticleByThematic(String articleId,String thematicId)throws Exception;

    /**
     * 查找状态
     */
    public ThematicArticle findArticleToThematicStatus(String articleId,String thematicId) throws Exception;

    /**
     * 查找专题包含的所有文章
     * @return
     * @throws Exception
     */
    public List<ThematicArticle> findAllArticleInThematic(String thematicId) throws Exception;

    /**
     * 查找文章所加入的全部专题
     * @param articleId
     * @return
     * @throws Exception
     */
    public List<ThematicArticle> findAllThematicInArticle(String articleId) throws Exception;

    /**
     * 查询用户创建的专题
     */
    public List<Thematic> findAllThematicInUser(String uid) throws Exception;

    /**
     * 删除专题
     */
    void deleteThematic(String thematicId)throws Exception;

    /**
     * 编辑专题资料
     */
    void updateThematicModify(Thematic thematic)throws Exception;

}
