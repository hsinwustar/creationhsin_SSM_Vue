package com.whx.creationhsin.service.impl;

import com.whx.creationhsin.dao.ThematicDao;
import com.whx.creationhsin.domain.Thematic;
import com.whx.creationhsin.domain.ThematicArticle;
import com.whx.creationhsin.service.ThematicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ThematicServiceImple implements ThematicService {

    @Autowired
    ThematicDao thematicDao;

    @Override
    public void AddThematic(Thematic thematic) throws Exception{
        thematicDao.AddThematic(thematic);
    }

    @Override
    public Thematic findThematicById(String thematicId) throws Exception {
        return thematicDao.findThematicById(thematicId);
    }

    @Override
    public List<Thematic> findAll() throws Exception{
        return thematicDao.findAll();
    }

    @Override
    public void addArticleToThematic(ThematicArticle thematicArticle) throws Exception{
        thematicDao.addArticleToThematic(thematicArticle);
    }

    @Override
    public void updateArticleByThematic(String articleId, String thematicId)throws Exception {
        thematicDao.updateArticleByThematic(articleId,thematicId);
    }

    @Override
    public ThematicArticle findArticleToThematicStatus(String articleId, String thematicId)throws Exception {
        return thematicDao.findArticleToThematicStatus(articleId,thematicId);
    }

    @Override
    public List<ThematicArticle> findAllArticleInThematic(String thematicId) throws Exception{
        return thematicDao.findAllArticleInThematic(thematicId);
    }

    @Override
    public List<ThematicArticle> findAllThematicInArticle(String articleId) throws Exception {
        return thematicDao.findAllThematicInArticle(articleId);
    }

    @Override
    public List<Thematic> findAllThematicInUser(String uid)throws Exception {
        return thematicDao.findAllThematicInUser(uid);
    }

    @Override
    public void deleteThematic(String thematicId)throws Exception {
        thematicDao.deleteThematic(thematicId);
    }

    @Override
    public void updateThematicModify(Thematic thematic)throws Exception {
        thematicDao.updateThematicModify(thematic);
    }
}
