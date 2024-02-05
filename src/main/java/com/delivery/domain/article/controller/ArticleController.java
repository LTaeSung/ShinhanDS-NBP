package com.delivery.domain.article.controller;

import com.delivery.domain.article.dto.ArticleDto;
import com.delivery.domain.article.entity.ArticleEntity;
import com.delivery.domain.article.repository.ArticleRepository;

import com.delivery.domain.comment.dto.CommentDto;
import com.delivery.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;



//@Tag(name = "게시판", description = "게시판 관련 api 입니다.")
@Controller // view 템플릿을 반환
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor // final로 명시된 필드를 DI 적용해준다, autowired보다 좋다~~~~~
public class ArticleController {

    // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결한다 -> DI

    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    // 글 생성 뷰 이동
    @GetMapping("/new")
    public String newArticleForm() {
        return "html/articles/new";
    }

    // 글 생성
    @PostMapping("/create")
    public String create(@ModelAttribute ArticleDto form) {
        // dto에 담겨짐
        // System.out.println(form.toString()); 로깅 바꾸기
        log.info(form.toString());

        // dto가 entity로 변환
        ArticleEntity article = form.toEntity();
//        System.out.println(article.toString()); 로깅 바꾸기
        log.info(article.toString());

        // entity를 repository 인터페이스 이용해서 db저장
        ArticleEntity saved = articleRepository.save(article);
//        System.out.println(saved.toString()); 로깅 바꾸기
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    // 데이터 1개 조회
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴, id값이 ArticleEntity 타입이 아니여서 Optional로 넣기
        Optional<ArticleEntity> articleEntity = articleRepository.findById(id);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 가져온 데이터를 모델에 등록
//        model.addAttribute("article", articleEntity.orElse(null));
        if (articleEntity.isPresent()) {
            model.addAttribute("article", articleEntity.get());
            model.addAttribute("commentDtods", commentDtos);
        }

        // 3. 보여줄 페이지 설정
        return "html/articles/show";
    }

    // 데이터 목록 조회
    @GetMapping("")
    public String list(Model model){
        // 모든 ArticleEntity 가져온다
        // List 대신 Iterable
        List<ArticleEntity> articleEntityList = articleRepository.findAll();
        log.info(articleEntityList.toString());

        // 가져온 ArticleEntity 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);
        // 뷰 페이지 설정
        return "html/articles/list";
    }

    // 데이터 수정하기
    // 수정 뷰 보여주기

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Optional<ArticleEntity> articleEntity = articleRepository.findById(id);
        // 모델에 데이터 등록
        //model.addAttribute("article", articleEntity.orElse(null));
        if (articleEntity.isPresent()) {
            model.addAttribute("article", articleEntity.get());
        }

        return "/html/articles/edit";
    }

    @PostMapping("/{id}/put")
    public String editPut(@ModelAttribute ArticleDto form){

        // dto를 entity로 변환
        ArticleEntity article = form.toEntity();
        log.info(form.toString());

        // entity를 db에 저장
        // 2-1 : db에서 기존 데이터를 가져오기
         Optional<ArticleEntity> target = articleRepository.findById(article.getId());
        // 2-2
        if (target.isPresent()) {
            articleRepository.save(article); // 엔티티가 db 갱신
        }
        // 수정결과를 뷰로 리다이렉트
        return "redirect:/articles/" + article.getId();
    }

    // 데이터 삭제하기
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어옴!");

        // 1: 삭제할 대상 가져오기
        Optional<ArticleEntity> target = articleRepository.findById(id);
        log.info(target.toString());
        // 2: 대상 삭제하기
        //  2-1 : 삭제 완료 메세지 -> RedirectAttributes 객체 사용
        //  삭제가 된 경우 addFlashAttribute("이름", "메세지") 일회성(휘발성) 메서드 사용가능
        if (target.isPresent()) {
            articleRepository.delete(target.get());
            rttr.addFlashAttribute("msg", "게시물이 삭제되었습니다.");
        }
        return "redirect:/articles";
    }


}
