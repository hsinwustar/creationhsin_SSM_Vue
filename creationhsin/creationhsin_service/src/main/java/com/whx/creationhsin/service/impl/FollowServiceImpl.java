package com.whx.creationhsin.service.impl;

import com.whx.creationhsin.dao.FollowDao;
import com.whx.creationhsin.domain.Follow;
import com.whx.creationhsin.domain.FollowArticle;
import com.whx.creationhsin.domain.FollowThematic;
import com.whx.creationhsin.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {

    @Autowired
    FollowDao followDao;

    @Override
    public void addFollowUser(Follow follow) throws Exception{
        followDao.addFollowUser(follow);
    }

    @Override
    public Follow findFollowUserStatus(String uid,String otherUserId) throws Exception{
        return followDao.findFollowUserStatus(uid,otherUserId);
    }

    @Override
    public void updateFollowStatus(String uid,String otherUserId)throws Exception {
        followDao.updateFollowStatus(uid,otherUserId);
    }

    @Override
    public void addFollowArticle(FollowArticle followArticle) throws Exception{
        followDao.addFollowArticle(followArticle);
    }

    @Override
    public List<FollowArticle> findFollowArticle(String uid) throws Exception{
        return followDao.findFollowArticle(uid);
    }

    @Override
    public void deleteFollowArticleStatus(String uid, String articleId) throws Exception{
        followDao.deleteFollowArticleStatus(uid,articleId);
    }

    @Override
    public FollowArticle findFollowArticleById(String uid, String articleId) throws Exception{
        return followDao.findFollowArticleById(uid,articleId);
    }

    @Override
    public void addFollowThematic(FollowThematic followThematic)throws Exception {
        followDao.addFollowThematic(followThematic);
    }

    @Override
    public void updateFollow(String uid, String thematicId)throws Exception {
        followDao.updateFollow(uid,thematicId);
    }

    @Override
    public FollowThematic findFollowThematicStatus(String uid, String thematicId)throws Exception {
        return followDao.findFollowThematicStatus(uid,thematicId);
    }

    @Override
    public List<FollowThematic> findFollowThematicOfUser(String thematicId)throws Exception{
        return followDao.findFollowThematicOfUser(thematicId);
    }

    @Override
    public List<Follow> findFollowUserOfOther(String otherUserId)throws Exception {
        return followDao.findFollowUserOfOther(otherUserId);
    }

    @Override
    public List<Follow> findFollowUserListOfPersonalFollow(String uid) throws Exception {
        return followDao.findFollowUserListOfPersonalFollow(uid);
    }

    @Override
    public List<FollowThematic> findFollowThematicListOfPersonalFollow(String uid)throws Exception {
        return followDao.findFollowThematicListOfPersonalFollow(uid);
    }
}
