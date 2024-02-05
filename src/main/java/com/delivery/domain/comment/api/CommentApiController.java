package com.delivery.domain.comment.api;

import com.delivery.domain.comment.dto.CommentDto;
import com.delivery.domain.comment.entity.CommentEntity;
import com.delivery.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에게 위임

        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과물 응답
        return (dtos != null) ?
                ResponseEntity.status(HttpStatus.OK).body(dtos) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // 댓글 생성

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto commentDto){
        // 서비스에게 위임
         CommentDto createDto = commentService.create(articleId, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    // 댓글 수정
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto commentDto){
        // 서비스에게 위임
        CommentDto updateDto = commentService.update(id, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 서비스에게 위임
        CommentDto deleteDto = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}
