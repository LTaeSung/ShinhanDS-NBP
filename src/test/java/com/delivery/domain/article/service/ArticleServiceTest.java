package com.delivery.domain.article.service;

import com.delivery.domain.article.entity.ArticleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 예상
        ArticleEntity a = new ArticleEntity(6L, "아자아자", "테스트");
        ArticleEntity b = new ArticleEntity(9L, "테스트 yml!", "test");
        ArticleEntity c = new ArticleEntity(10L, "test", "테스트 api");
        ArticleEntity d = new ArticleEntity(11L, "테스트 입니다!!", "아장자ㅏ");
        List<ArticleEntity> expected = new ArrayList<ArticleEntity>(Arrays.asList(a, b, c,d));
        // 실제
        List<ArticleEntity> articleEntityList = articleService.index();
        // 비교
        assertEquals(expected.toString(), articleEntityList.toString());
    }

    @Test
    void show_success() {
        // 예상
        Long id = 6L;
        ArticleEntity expected = new ArticleEntity(id, "아자아자", "테스트");
        // 실제
        Optional<ArticleEntity> article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.get().toString());
    }

//    @Test
//    void  show_fail(){
//        // 예상
//        Long id = -1L;
//        ArticleEntity expected = null;
//        // 실제
//        Optional<ArticleEntity> article = articleService.show(id);
//        // 비교
//        assertEquals(expected, article);
//    }

}