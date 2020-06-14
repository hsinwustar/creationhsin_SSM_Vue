package com.whx.creationhsin.service.impl;

import com.whx.creationhsin.dao.CommentDao;
import com.whx.creationhsin.domain.Comment;
import com.whx.creationhsin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> findCommentById(String commentId) throws Exception{
        return commentDao.findCommentById(commentId);
    }

    @Override
    public List<Comment> findCommentByarticleId(String articleId) throws Exception{
        return commentDao.findCommentByarticleId(articleId);
    }

    @Override
    public List<Comment> findCommentByUId(String uid) throws Exception {
        return commentDao.findCommentByUId(uid);
    }

    @Override
    public void addComment(Comment comment) throws Exception {
        commentDao.addComment(comment);
    }


    @Override
    public List<Comment> findCommentByUIdandArticleId(String uid)throws Exception {

        return commentDao.findCommentByUIdandArticleId(uid);
    }
}
