package com.sparta.week06.repository;

import com.sparta.week06.domain.Comment;
import com.sparta.week06.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdOrderByModifiedAtDesc(Long postId);
}
