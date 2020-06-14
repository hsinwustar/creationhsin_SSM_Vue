package com.whx.creationhsin.service;

import com.whx.creationhsin.domain.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 根据评论id查询评论
     */
    public List<Comment> findCommentById(String commentId) throws Exception;

    /**
     * 根据文章id查询评论
     */
    public List<Comment> findCommentByarticleId(String articleId) throws Exception;

    /**
     * 根据用户id查询评论
     */
    public List<Comment> findCommentByUId(String uid) throws Exception;

    /**
     * 添加评论
     */
    void addComment(Comment comment) throws Exception;

    public List<Comment> findCommentByUIdandArticleId(String uid) throws Exception;

}
