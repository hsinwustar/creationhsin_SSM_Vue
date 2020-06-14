package com.whx.creationhsin.service;

import com.whx.creationhsin.domain.Follow;
import com.whx.creationhsin.domain.FollowArticle;
import com.whx.creationhsin.domain.FollowThematic;

import java.util.List;

public interface FollowService {

    /**
     * 关注作者
     */
    void addFollowUser(Follow follow) throws Exception;

    /**
     * 查找状态
     */
    public Follow findFollowUserStatus(String uid,String otherUserId) throws Exception;

    /**
     * 取消关注
     */
    void updateFollowStatus(String uid,String otherUserId)throws Exception;

    /**
     * 收藏文章
     */
    void addFollowArticle(FollowArticle followArticle) throws Exception;

    /**
     * 根据用户id查询收藏的文章
     */
    public List<FollowArticle> findFollowArticle(String uid) throws Exception;

    /**
     * 根据用户id和文章id判断是否收藏文章
     */
    public FollowArticle findFollowArticleById(String uid,String articleId) throws Exception;

    /**
     * 取消收藏文章
     */
    void deleteFollowArticleStatus(String uid,String articleId)throws Exception;

    /**
     * 关注专题
     */
    void addFollowThematic(FollowThematic followThematic) throws Exception;


    /**
     * 取消关注
     */
    void updateFollow(String uid,String thematicId)throws Exception;

    /**
     * 查找状态
     */
    public FollowThematic findFollowThematicStatus(String uid,String thematicId) throws Exception;

    /**
     * 查找专题中的关注用户
     */
    public List<FollowThematic> findFollowThematicOfUser(String thematicId) throws Exception;

    /**
     * 查找用户页面显示的关注信息
     */

    public List<Follow> findFollowUserOfOther(String otherUserId) throws Exception;

    /**
     * 根据用户查找关注了哪些人
     * @param uid
     * @return
     * @throws Exception
     */
    public List<Follow> findFollowUserListOfPersonalFollow(String uid) throws Exception;


    /**
     * 根据用户查找关注了哪些专题
     * @param uid
     * @return
     * @throws Exception
     */
    public List<FollowThematic> findFollowThematicListOfPersonalFollow(String uid) throws Exception;


}
