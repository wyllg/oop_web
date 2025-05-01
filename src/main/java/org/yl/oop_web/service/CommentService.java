package org.yl.oop_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yl.oop_web.model.Comment;
import org.yl.oop_web.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getTopLevelComments(Long adviceId) {
        return commentRepository.findByAdviceIdAndParentCommentIsNull(adviceId);
    }

    public List<Comment> getReplies(Long parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}

