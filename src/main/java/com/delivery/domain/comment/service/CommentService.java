package com.delivery.domain.comment.service;

import com.delivery.domain.article.entity.ArticleEntity;
import com.delivery.domain.article.repository.ArticleRepository;
import com.delivery.domain.comment.dto.CommentDto;
import com.delivery.domain.comment.entity.CommentEntity;
import com.delivery.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(commentEntity -> CommentDto.createCommentDto(commentEntity))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto commentDto) {
        // 게시글 조회 및 예외 발생
        ArticleEntity articleEntity = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!, 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        CommentEntity comment = CommentEntity.createComent(commentDto, articleEntity);
        // 댓글 엔티티를 DB로 저장
        CommentEntity created = commentRepository.save(comment);
        // DTO로 변경하여 변환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto commentDto) {
        // 댓글 조회 및 예외 발생
        CommentEntity target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패, 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(commentDto);
        // DB 갱신
        CommentEntity updated = commentRepository.save(target);
        // 댓글 엔티티 dto로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외
        CommentEntity target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패, 대상 댓글이 없습니다."));
        // 삭제
        commentRepository.delete(target);
        // 삭제 댓글 DTO 변환
        return CommentDto.createCommentDto(target);
    }
}
