package com.delivery.domain.comment.dto;

import com.delivery.domain.comment.entity.CommentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
public class CommentDto {
    private Long id;

    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(CommentEntity c) {
        return new CommentDto(
                c.getId(),
                c.getArticleEntity().getId(),
                c.getNickName(),
                c.getBody()
        );
    }
}
