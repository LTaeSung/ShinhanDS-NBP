package com.delivery.domain.comment.entity;

import com.delivery.domain.article.entity.ArticleEntity;
import com.delivery.domain.comment.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 해당 댓글 엔티티 여러개가, 하나의 ArticleEntity에 연관
    @JoinColumn(name = "article_id") // articleid 컬럼에 Article 대표값을 저장
    private ArticleEntity articleEntity;

    @Column
    private String nickName;
    @Column
    private  String body;

    public static CommentEntity createComent(CommentDto commentDto, ArticleEntity articleEntity) {
        // 예외처리
        if (commentDto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패, 댓글의 id가 없어야함");
        }
        if (commentDto.getArticleId() != articleEntity.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패, 게시글의 id가 잘못되었습니다.");
        }
        return new CommentEntity(
                commentDto.getId(),
                articleEntity,
                commentDto.getNickname(),
                commentDto.getBody()
        );
    }

    public void patch(CommentDto commentDto) {
        if (this.id != commentDto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패!, 잘못된 id가 입력!");
        }
        if (commentDto.getNickname() !=null) {
            this.nickName = commentDto.getNickname();
        }
        if (commentDto.getBody() != null) {
            this.body = commentDto.getBody();
        }
    }
}
