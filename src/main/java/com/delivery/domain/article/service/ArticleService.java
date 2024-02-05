package com.delivery.domain.article.service;

import com.delivery.domain.article.dto.ArticleDto;
import com.delivery.domain.article.entity.ArticleEntity;
import com.delivery.domain.article.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // 서비스 선언 (서비스 객체를 스프링 부트에 생성하는 코드)
@Slf4j
public class ArticleService {

    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<ArticleEntity> index() {
        return articleRepository.findAll();
    }

    public Optional<ArticleEntity> show(Long id) {
        return articleRepository.findById(id);
    }

    public ArticleEntity create(ArticleDto dto) {
        ArticleEntity article = dto.toEntity();
        // db가 만들어주므로 id가 post가 된다면 null로 무시하기
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public ArticleEntity update(Long id, ArticleDto dto) {

        // 1. 수정용 엔티티 생성
        ArticleEntity article = dto.toEntity();
        log.info("id: {}, article: {}",id,article.toString());

        // 2. 대상 엔티티 조회
        Optional<ArticleEntity> target = articleRepository.findById(id);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 없을 경우)
        if (!(target.isPresent()) || id != article.getId()) {
            // 400 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}",id,article.toString());
            // HttpStatus.BAD_REQUEST ==> 400, body에는 안담는다 ==> null
            return null;
        }
        // 4. 업데이트 및 정상 응답
        if (target.isPresent()) {
            ArticleEntity targetMain = target.get();
            targetMain.patch(article);
            ArticleEntity update = articleRepository.save(targetMain);
            return update;
        }
        // 어떠한 경우에 업데이트 에러가 난다
        return null;
    }

    public ArticleEntity delete(Long id) {
        // 1. 찾기
        Optional<ArticleEntity> target = articleRepository.findById(id);

        if (!(target.isPresent())){
            return null;
        }
        // 2. 삭제
        articleRepository.delete(target.get());
        // 3. 반환
        return target.get();
    }


    // 트랜잭션
    @Transactional // 해당 메서드를 트랜잭션으로 묶는다!, 실패하면 메서드 수행 이전 상태로 롤백(Rollback)한다
    public List<ArticleEntity> createArticles(List<ArticleDto> dtos) {
        // dto 묶음을 entity 묶음으로 바꾸기
        List<ArticleEntity> articleEntityList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());

        // entity 묶음을 db에 저장
        articleEntityList.stream().forEach(articleEntity -> articleRepository.save(articleEntity));
        // 강제 예외
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );
        // 결과값 반환
        return articleEntityList;
    }
}

