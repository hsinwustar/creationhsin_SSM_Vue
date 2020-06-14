package com.whx.creationhsin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.whx.creationhsin.domain.Article;
import com.whx.creationhsin.domain.Like;
import com.whx.creationhsin.domain.PageBean;
import com.whx.creationhsin.service.ArticleService;
import com.whx.creationhsin.utils.LikeUtils;
import com.whx.creationhsin.utils.MakeTimeUtils;
import com.whx.creationhsin.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Controller
@RequestMapping("/article")
@ResponseBody
@CrossOrigin(origins="*",maxAge=3600)
public class ArticleController {

    private int pageSize=10;
    private int likeNum = 0;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UUIDUtils uuidUtils;

    @Autowired
    private MakeTimeUtils makeTimeUtils;

    @Autowired
    private LikeUtils likeUtils;

//    private boolean flag = false;

    @RequestMapping("/findAll")
    public List<Article> findAll()throws Exception{
//        flag = false;
        return articleService.findAll();
    }
    @RequestMapping("/findAll2")
    public List<Article> findAll2()throws Exception{
//        flag = false;
        return articleService.findAll2();
    }

    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(@RequestParam("pageCode") int pageCode, @RequestParam("pageSize") int pageSize) throws Exception{
        return articleService.findByPage(pageCode, pageSize);
    }


    @RequestMapping("/findArticleById")
    public Article findArticleById(@RequestParam(name = "articleId") String articleId)throws Exception{
        return articleService.findArticleById(articleId);
    }


   @RequestMapping("/addArticle")
   public void addArticle(@RequestBody Article article,RedirectAttributes attr) throws Exception{
       article.setArticleId(uuidUtils.getUUID());
       article.setTime(makeTimeUtils.getDatetime());
       articleService.addArticle(article);
       System.out.println("article:::::::::" + article);
       attr.addAttribute("articleIdByAdd", article.getArticleId());
       System.out.println("articleIdByAdd:::::::::" + article.getArticleId());
   }


   @RequestMapping("/updateArticleMessage")
   public String updateArticleMessage(@RequestBody Article article, RedirectAttributes attr) throws Exception{
       articleService.updateArticleMessage(article);
       attr.addAttribute("articleId", article.getArticleId());
       return "success";
   }


   @RequestMapping("/deleteArticleById")
   public void deleteArticleById(@RequestParam(name = "articleId") String articleId) throws Exception{
       articleService.deleteArticleById(articleId);
   }

    @RequestMapping("/findArticleByUId")
    public List<Article> findArticleByUId(@RequestParam(name = "uid") String uid)throws Exception{
        return articleService.findArticleByUId(uid);
    }

    @RequestMapping("/findArticleByUIdAll")
    public List<Article> findArticleByUIdAll(@RequestParam(name = "uid") String uid)throws Exception{
        return articleService.findArticleByUIdAll(uid);
    }

    @RequestMapping("/updateArticleLike")
    public void updateArticleLike(@RequestBody Article article) throws Exception {
//        if(flag == true){
////            article.setLikeNum(article.getLikeNum()+1);
////
//////        article.setArticleId(like.getArticleId());
////            article.setLike(article.getLikeNum());
////            articleService.updateArticleLike(article);
////        }else if(flag == false) {
////            article.setLikeNum(article.getLikeNum()-1);
////
//////        article.setArticleId(articleId);
////            article.setLike(article.getLikeNum());
////            articleService.updateArticleLikeSub(article);
////        }
    }

    @RequestMapping("/updateArticleLikeSub")
    public void updateArticleLikeSub(@RequestBody Article article) throws Exception {
        article.setLikeNum(article.getLikeNum()-1);
        articleService.updateArticleLike(article);
        article.setLike(article.getLikeNum());
        articleService.updateArticleLikeSub(article);
    }

    @RequestMapping("/addArticleLike")
    public String addArticleLike(@RequestBody Like like, RedirectAttributes attr) throws Exception {
        articleService.addArticleLike(like);
        likeNum+=1;
        Article article = new Article();
        article.setArticleId(like.getArticleId());
        article.setLike(likeNum);
        articleService.updateArticleLike(article);
        attr.addAttribute("articleId", like.getArticleId());
        return "success";
    }

    @ResponseBody
    @RequestMapping("/deleteLikeArticleStatus")
    public void deleteLikeArticleStatus(@RequestParam(name = "uid") String uid,@RequestParam(name = "articleId") String articleId) throws Exception{
        articleService.deleteLikeArticleStatus(uid,articleId);
        likeNum-=1;
        Article article = new Article();
        article.setArticleId(articleId);
        article.setLike(likeNum);
        articleService.updateArticleLike(article);
    }

    @ResponseBody
    @RequestMapping("/findArticleLikeById")
    public Like findArticleLikeById(@RequestParam(name = "uid") String uid,@RequestParam(name = "articleId") String articleId)throws Exception{
        return articleService.findArticleLikeById(uid,articleId);
    }

}
