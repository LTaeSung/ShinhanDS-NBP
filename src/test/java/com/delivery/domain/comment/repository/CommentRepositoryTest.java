package com.delivery.domain.comment.repository;

import com.delivery.domain.article.entity.ArticleEntity;
import com.delivery.domain.comment.entity.CommentEntity;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest // JPA와 연동한 테스트, Repository 테스트이기 때문
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트 디비
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<CommentEntity> commentEntityList = commentRepository.findByArticleId(articleId);
            // 예상
            ArticleEntity articleEntity = new ArticleEntity(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            CommentEntity a = new CommentEntity(1L, articleEntity, "kky", "위대한 쇼맨");
            CommentEntity b = new CommentEntity(2L, articleEntity, "son", "탑건 2");
            CommentEntity c = new CommentEntity(3L, articleEntity, "lee", "나는 내일, 어제의 너와 만난다");

            List<CommentEntity> expected = Arrays.asList(a, b, c);
            // 검증
            // 개행 문자를 제외한 문자열로 비교
            String expectedString = expected.toString().replace("\n", "").replace("\r", "");
            String actualString = commentEntityList.toString().replace("\n", "").replace("\r", "");

            // 출력 추가
            System.out.println("Expected: " + expectedString);
            System.out.println("Actual  : " + actualString);

            // 검증
            assertEquals(expectedString, actualString, "4번 글의 모든 댓글 출력");
            // 수정된 코드: 줄바꿈 문자를 없애고 비교
//            assertEquals(expected.toString().replace("\n", ""), commentEntityList.toString().replace("\n", ""), "4번 글의 모든 댓글 출력");
//            assertEquals(expected.toString(), commentEntityList.toString(),"4번 글의 모든 댓글 출력");
        }
    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        {
            // 입력 데이터
            String nickname = "kky";
            // 실제 수행
            List<CommentEntity> commentEntityList = commentRepository.findByNickname(nickname);
            // 예상
            CommentEntity a = new CommentEntity(1L, new ArticleEntity(4L,"당신의 인생 영화는?","댓글 ㄱ"),nickname,"위대한 쇼맨");
            CommentEntity b = new CommentEntity(4L, new ArticleEntity(5L,"당신의 소울 푸드는?","댓글 ㄱㄱ"),nickname,"소고기");
            CommentEntity c = new CommentEntity(7L, new ArticleEntity(6L,"당신의 취미는?","댓글 ㄱㄱㄱ"),nickname,"헬스");
            List<CommentEntity> expected = Arrays.asList(a, b, c);

            // 개행 문자를 제외한 문자열로 비교
            String expectedString = expected.toString().replace("\n", "").replace("\r", "");
            String actualString = commentEntityList.toString().replace("\n", "").replace("\r", "");

            // 출력 추가
            System.out.println("Expected: " + expectedString);
            System.out.println("Actual  : " + actualString);

            // 검증
            assertEquals(expectedString, actualString, "kky의 모든 댓글 출력");
            // 검증
//            assertEquals(expected.toString(), commentEntityList.toString(),"kky의 모든 댓글!");
        }
    }
}