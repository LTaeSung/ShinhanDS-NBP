package com.delivery.domain.article.api;

import com.delivery.domain.article.dto.ArticleDto;
import com.delivery.domain.article.entity.ArticleEntity;
import com.delivery.domain.article.repository.ArticleRepository;
import com.delivery.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController // RestAPI 용 컨트롤러, 데이터 JSON을 반환
@RequestMapping("/api/articles")
@Slf4j
@RequiredArgsConstructor
public class ArticleApiController {

    // DI 외부에서 가져옴, 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결한다
    private final ArticleService articleService;

    // GET
    // 모든 api 가져오기
    @GetMapping("/")
    public List<ArticleEntity> index() {
        return articleService.index(); //JSON
    }

    // 단일 api
    @GetMapping("/{id}")
    public Optional<ArticleEntity> show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/")
    public ResponseEntity<ArticleEntity> create(@RequestBody ArticleDto dto) {
        ArticleEntity created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) : //200
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //400
    }


    // PATCH - 일부 변경
    // ResponseEntity 타입은 http 상태코드를 같이 보낸다
    @PatchMapping("/{id}")
    public ResponseEntity<ArticleEntity> update(@PathVariable Long id, @RequestBody ArticleDto dto) {

        ArticleEntity updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleEntity> delete(@PathVariable Long id) {

        ArticleEntity deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/transaction-test")
    public ResponseEntity<List<ArticleEntity>> transactionTest(@RequestBody List<ArticleDto> dtos) {
        List<ArticleEntity> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

