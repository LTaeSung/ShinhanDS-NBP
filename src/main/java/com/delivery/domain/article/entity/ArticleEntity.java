package com.delivery.domain.article.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity // DB가 해당 객체 인식 가능!
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ArticleEntity {


    @Id // 대표값을 지정! 마치 주민번호처럼
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3 처럼 auto_increase
    private Long id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String content;

    // 수정을 할 때 전부 안하고 1개만 해도 가능하게 해줌
    public void patch(ArticleEntity articleEntity){
        if (articleEntity.title != null) {
            this.title = articleEntity.title;
        }
        if (articleEntity.content != null) {
            this.content = articleEntity.content;
        }
    }

}
