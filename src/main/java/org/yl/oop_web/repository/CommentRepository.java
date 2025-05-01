package org.yl.oop_web.repository;

import org.yl.oop_web.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAdviceIdAndParentCommentIsNull(Long adviceId);
    List<Comment> findByParentCommentId(Long parentCommentId);
}

