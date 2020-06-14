package com.whx.creationhsin.controller;

import com.whx.creationhsin.domain.Comment;
import com.whx.creationhsin.service.CommentService;
import com.whx.creationhsin.utils.MakeTimeUtils;
import com.whx.creationhsin.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@ResponseBody
@CrossOrigin(origins="*",maxAge=3600)
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    private UUIDUtils uuidUtils;
    @Autowired
    private MakeTimeUtils makeTimeUtils;

    /**
     * 根据评论id查询评论
     * @param commentId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findCommentById")
    public List<Comment> findCommentById(@RequestParam(name = "commentId") String commentId)throws Exception{
        return commentService.findCommentById(commentId);
    }

    /**
     * 根据文章id查询评论
     */
    @RequestMapping("/findCommentByarticleId")
    public List<Comment> findCommentByarticleId(@RequestParam(name = "articleId") String articleId)throws Exception{
        return commentService.findCommentByarticleId(articleId);
    }

    /**
     * 根据用户id查询评论
     */
    @RequestMapping("/findCommentByUId")
    public List<Comment> findCommentByUId(@RequestParam(name = "uid") String uid)throws Exception{
        return commentService.findCommentByUId(uid);
    }

    /**
     * 添加评论
     */
    @RequestMapping("/addComment")
    public void addComment(@RequestBody Comment comment) throws Exception{
        comment.setCommentId(uuidUtils.getUUID());
        comment.setTime(makeTimeUtils.getDatetime());
        commentService.addComment(comment);
        System.out.println("article:::::::::" + comment);
    }

    /**
     * 回复评论
     */
    @RequestMapping("/addCommentRemark")
    public void addCommentRemark(@RequestBody Comment comment) throws Exception{
        commentService.addComment(comment);
        System.out.println("article:::::::::" + comment);
    }

    @RequestMapping("/findCommentByUIdandArticleId")
    public List<Comment> findCommentByUIdandArticleId(@RequestParam(name = "uid") String uid)throws Exception{
        return commentService.findCommentByUIdandArticleId(uid);
    }

}
