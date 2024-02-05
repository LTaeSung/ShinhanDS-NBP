package com.delivery.domain.article.dto;

import com.delivery.domain.article.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleDto {

    private Long id;
    private String title;
    private String content;


    public ArticleEntity toEntity() {
        return new ArticleEntity(id,title, content);
    }


}
