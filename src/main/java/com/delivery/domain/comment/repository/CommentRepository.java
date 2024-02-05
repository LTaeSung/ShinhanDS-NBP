package com.delivery.domain.comment.repository;

import com.delivery.domain.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<CommentEntity> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE nick_name = :nickname", nativeQuery = true)
    List<CommentEntity> findByNickname(String nickname);
}

