package com.delivery.domain.owner.controller;


import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import com.delivery.domain.owner.dto.OwnerDTO;
import com.delivery.domain.owner.service.OwnerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor // 컨트롤러가 서비스의 메서드등을 사용할 수 있는 권한이 생김
public class OwnerController {

    // 생성자 주입
    private final OwnerService ownerService;
    private final StoreRepository storeRepository;


    // 회원가입 페이지 출력 요청
    @GetMapping("/owner/save") // 메인페이지에서 로그인한다고 href 건거 (링크 건건 거의 get)
    public String saveForm() {
        return "html/owner/save";  // 회원가입 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/owner/save") // save.html 에서 받아온 post 매핑
    // 받아온 dto 객체들을 서비스 - 레포지토리 - 디비
    // @ModelAttribute가
    // @RequestParam("memberEmail") String memberemail, password ... 다 받아올걸 한번에 받아온 느낌
    public String save(@ModelAttribute OwnerDTO ownerDTO) {
        System.out.println("OwnerController.save");
        System.out.println("OwnerDTO = " + ownerDTO);
        ownerService.save(ownerDTO);  // 회원가입 정보를 서비스로 전달
        return "html/owner/login";  // 가입 끝났으면 로그인 페이지로 이동
    }

    @GetMapping("/owner/login")
    public String loginForm() {
        return "html/owner/login";  // 로그인 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/owner/login")
    public String login(@ModelAttribute OwnerDTO ownerDTO, HttpSession session
                        , @RequestParam(defaultValue = "/") String redirectURL) {

        OwnerDTO loginResult = ownerService.login(ownerDTO);

        log.info(String.valueOf(loginResult));

        if (loginResult == null) {
            System.out.println("아이디 틀렸다~!");
            return "html/owner/login";
        }

        session.setAttribute("ownerId", loginResult.getId());
        session.setAttribute("ownerLoginEmail", loginResult.getOwnerEmail());
        session.setAttribute("ownerLoginName", loginResult.getOwnerName());
        // 직전 페이지의 정보를 들고 와야됨
        return "redirect:/owner/";


    }

    @GetMapping(value = "/owner/")
//    @PathVariable Long articleId
    public String ownerHome(Model model, HttpSession session){
        Long newOwnerId = (Long) session.getAttribute("ownerId");
        OwnerDTO ownerDTO = ownerService.findById(newOwnerId);
        // owner의 값 불러옴 3번 - 채원이
        log.info("bb - " + ownerDTO.toString());
        Optional<StoreEntity> dummyStoreEntity = storeRepository.findByOwnerEntity_Id(newOwnerId);
        log.info("aa - " + dummyStoreEntity.toString());
        model.addAttribute("owner", ownerDTO);  // 모델에 회원 정보를 담아서 전달
//        if (!dummyStoreEntity.isEmpty()) {
//            DummyStoreEntity dummyStore = dummyStoreEntity.get(0);
//            model.addAttribute("ownerStore", dummyStore);
//        }
        dummyStoreEntity.ifPresent(store -> model.addAttribute("ownerStore", store));

        // 아이디에 해당하는 회원 정보 조회 (한명 걍 dto)
        session.setAttribute("loginName", ownerDTO.getOwnerName());


        // dummyStoreEntity가 null이 아니고 존재하면 모델에 추가
//        dummyStoreEntity.ifPresent(entity -> model.addAttribute("stores", Collections.singletonList(entity)));
        return "layouts/owner/ownerNewMain";

    }

    @GetMapping("/loginOwnerhome")
    public String loginHome(HttpSession session) {

        return "loginOwnerhome";  // 회원 상세 정보 페이지의 HTML 파일명 리턴
    }

    @GetMapping("/owner/list")
    public String findAll(Model model) {
        List<OwnerDTO> ownerDTOList = ownerService.findAll();  // 모든 회원 정보 조회 (여러명 list)
        model.addAttribute("ownerList", ownerDTOList);  // 모델에 회원 목록을 담아서 전달
        return "html/owner/list";  // 회원 목록 페이지의 HTML 파일명 리턴
    }

//    @GetMapping("/owner/{id}")
//    public String findById(@PathVariable Long id, Model model, HttpSession session) {
//        OwnerDTO ownerDTO = ownerService.findById(id);  // 아이디에 해당하는 회원 정보 조회 (한명 걍 dto)
//        model.addAttribute("owner", ownerDTO);  // 모델에 회원 정보를 담아서 전달
//        session.setAttribute("loginName", ownerDTO.getOwnerName());
//        return "html/owner/detail";  // 회원 상세 정보 페이지의 HTML 파일명 리턴
//    }

    @GetMapping("/owner/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");  // 세션에서 로그인 이메일 가져오기
        OwnerDTO ownerDTO = ownerService.updateForm(myEmail);  // 내 정보 수정을 위한 회원 정보 조회
        model.addAttribute("updateOwner", ownerDTO);  // 모델에 수정할 회원 정보를 담아서 전달
        return "html/owner/update";  // 회원 정보 수정 페이지의 HTML 파일명 리턴ㅁ
    }

    @PostMapping("/owner/update")
    public String update(@ModelAttribute OwnerDTO ownerDTO) {
        ownerService.update(ownerDTO);  // 회원 정보 수정
        // 수정된 회원 정보 페이지로 리다이렉트  @GetMapping("/member/{id}") 이 부분으로 가는거임
        // 그냥 냅다 주소로 가면 여기선 model 에 값을 안받아 뒀기에  ( model.addAttribute("member", memberDTO);)
        // ${member.id} 이 부분이 안보일거임 ㅇㅇ 그래서 리다이렉트로 하는거다~
        return "redirect:/owner/" + ownerDTO.getId();
    }

    @GetMapping("/owner/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        ownerService.deleteById(id);  // 아이디에 해당하는 회원 정보 삭제
        return "redirect:/owner/";  // 회원 목록 페이지로 리다이렉트
    }

    @GetMapping("/owner/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 무효화
        return "/html/owner/indexOwner";  // 인덱스 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/owner/email-check")
    //ajax 쓸땐 @ResponseBody로 함
    public @ResponseBody String emailCheck(@RequestParam("ownerEmail") String ownerEmail) {
        System.out.println("ownerEmail = " + ownerEmail);
        String checkResult = ownerService.emailCheck(ownerEmail);  // 이메일 중복 체크
        return checkResult;  // 중복 여부에 따른 결과 리턴
    }
}

