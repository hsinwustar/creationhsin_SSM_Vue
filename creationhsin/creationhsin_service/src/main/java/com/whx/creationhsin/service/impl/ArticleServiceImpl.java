package com.whx.creationhsin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whx.creationhsin.dao.ArticleDao;
import com.whx.creationhsin.domain.Article;
import com.whx.creationhsin.domain.Like;
import com.whx.creationhsin.domain.PageBean;
import com.whx.creationhsin.service.ArticleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 查询所有文章
     * @return
     * @throws Exception
     */

    @Override
    public List<Article> findAll() throws Exception{
        return articleDao.findAll();
    }

    @Override
    public List<Article> findAll2() throws Exception{
        return articleDao.findAll2();
    }

    /**
     * 分页查询
     * @param pageCode
     * @param pageSize
     * @return
     */

    @Override
    public PageBean findByPage(int pageCode, int pageSize) throws Exception{
        PageHelper.startPage(pageCode,pageSize);
        Page<Article> page = (Page<Article>) articleDao.findByPage();
        return new PageBean(page.getTotal(),page.getResult());
    }



    /**
     * 根据articleId来查询文章
     * @param articleId
     * @return
     */
    @Override
    public Article findArticleById(String articleId) throws Exception{
        return articleDao.findArticleById(articleId);
    }

    /**
     * 添加文章
     * @param article
     * @throws Exception
     */
    @Override
    public void addArticle(Article article) throws Exception {
        articleDao.addArticle(article);
    }



    /**
     * 点赞数增加
     * @param article
     */
    @Override
    public void updateArticleLike(Article article) throws Exception{
        articleDao.updateArticleLike(article);
    }

    /**
     * 点赞数减少
     * @param article
     * @throws Exception
     */
    @Override
    public void updateArticleLikeSub(Article article)throws Exception {
        articleDao.updateArticleLikeSub(article);
    }

    /**
     * 点赞
     */
    @Override
    public void addArticleLike(Like like)throws Exception {
        articleDao.addArticleLike(like);
    }

    /**
     * 取消点赞
     */
    @Override
    public void deleteLikeArticleStatus(String uid, String articleId)throws Exception {
        articleDao.deleteLikeArticleStatus(uid,articleId);
    }

    /**
     * 根据用户id和文章id对点赞进行判断，禁止重复点赞
     */
    @Override
    public Like findArticleLikeById(String uid, String articleId)throws Exception {
        return articleDao.findArticleLikeById(uid,articleId);
    }

    /**
     * 修改文章信息
     * @param article
     * @throws Exception
     */
    @Override
    public void updateArticleMessage(Article article) throws Exception {
        articleDao.updateArticleMessage(article);
    }


    /**
     * 删除文章
     * @param articleId
     * @throws Exception
     */
    @Override
    public void deleteArticleById(String articleId) throws Exception {
        articleDao.deleteArticleById(articleId);
    }


    @Override
    public List<Article> findArticleByUId(String uid) throws Exception {
        return articleDao.findArticleByUId(uid);
    }

    @Override
    public List<Article> findArticleByUIdAll(String uid)throws Exception {
        return articleDao.findArticleByUIdAll(uid);
    }
}
