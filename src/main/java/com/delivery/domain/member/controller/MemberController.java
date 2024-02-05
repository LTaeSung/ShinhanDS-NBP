package com.delivery.domain.member.controller;

import com.delivery.domain.store.repository.StoreRepository;
import com.delivery.domain.member.dto.MemberDTO;
import com.delivery.domain.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor // 컨트롤러가 서비스의 메서드등을 사용할 수 있는 권한이 생김
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;
    private final StoreRepository storeRepository;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save") // 메인페이지에서 로그인한다고 href 건거 (링크 건건 거의 get)
    public String saveForm() {
        return "html/member/save";  // 회원가입 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/member/save") // save.html 에서 받아온 post 매핑
    // 받아온 dto 객체들을 서비스 - 레포지토리 - 디비
    // @ModelAttribute가
    // @RequestParam("memberEmail") String memberemail, password ... 다 받아올걸 한번에 받아온 느낌
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);  // 회원가입 정보를 서비스로 전달
        return "html/member/login";  // 가입 끝났으면 로그인 페이지로 이동
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "html/member/login";  // 로그인 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session
                        , Model model) {

        // 디비에 담긴 가게 가져오기 5줄
//        List<DummyStoreEntity> dummyStoreEntity = dummyStoreRepository.findAll();
//        log.info(dummyStoreEntity.toString());
//        session.setAttribute("stores", dummyStoreEntity);
//        List<String> store = (List<String>) session.getAttribute("stores");
//        model.addAttribute("stores", store);

        MemberDTO loginResult = memberService.login(memberDTO);
        log.info(String.valueOf(loginResult));

        boolean isLoggedIn = true;


        if (loginResult == null) {
            System.out.println("아이디 틀렸다~!");
            isLoggedIn = false;
            return "html/member/login";
        }
        session.setAttribute("loginEmail", loginResult.getMemberEmail());
        session.setAttribute("loginName", loginResult.getMemberName());
        session.setAttribute("loginPhone", loginResult.getMemberPhone());
        session.setAttribute("loginAddress" , loginResult.getMemberAddress());
        session.setAttribute("LoggedIn", isLoggedIn);

        model.addAttribute("loginName", loginResult.getMemberName());
        model.addAttribute("loggedIn", isLoggedIn);

        log.info("login session loginEmail: " + session.getAttribute("loginEmail"));


        String redirectURL = (String) session.getAttribute("redirectURL");
        session.removeAttribute("redirectURL");
        // 직전 페이지의 정보를 들고 와야됨
        //return "redirect:" + redirectURL;
//        return "redirect:" + (redirectURL != null ? redirectURL : "/");
        return "redirect:/customer/";

    }

    @GetMapping("/loginhome")
    public String loginHome(HttpSession session) {

        return "html/member/loginhome";  // 회원 상세 정보 페이지의 HTML 파일명 리턴
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();  // 모든 회원 정보 조회 (여러명 list)
        model.addAttribute("memberList", memberDTOList);  // 모델에 회원 목록을 담아서 전달
        return "html/member/list";  // 회원 목록 페이지의 HTML 파일명 리턴
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model, HttpSession session) {
        MemberDTO memberDTO = memberService.findById(id);  // 아이디에 해당하는 회원 정보 조회 (한명 걍 dto)
        model.addAttribute("member", memberDTO);  // 모델에 회원 정보를 담아서 전달
        session.setAttribute("loginName", memberDTO.getMemberName());

        session.setAttribute("loginId", memberDTO.getId());

        log.info("상세조회 session loginEmail: " + session.getAttribute("loginEmail"));
        return "html/member/detail";  // 회원 상세 정보 페이지의 HTML 파일명 리턴
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");  // 세션에서 로그인 이메일 가져오기
        MemberDTO memberDTO = memberService.updateForm(myEmail);  // 내 정보 수정을 위한 회원 정보 조회
        model.addAttribute("updateMember", memberDTO);  // 모델에 수정할 회원 정보를 담아서 전달

        log.info("updateform session loginEmail: " + session.getAttribute("loginEmail"));
        return "html/member/update";  // 회원 정보 수정 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        memberService.update(memberDTO);  // 회원 정보 수정
        // 수정된 회원 정보 페이지로 리다이렉트  @GetMapping("/member/{id}") 이 부분으로 가는거임
        // 그냥 냅다 주소로 가면 여기선 model 에 값을 안받아 뒀기에  ( model.addAttribute("member", memberDTO);)
        // ${member.id} 이 부분이 안보일거임 ㅇㅇ 그래서 리다이렉트로 하는거다~

        log.info("update session loginEmail: " + session.getAttribute("loginEmail"));
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);  // 아이디에 해당하는 회원 정보 삭제
        return "redirect:/member/";  // 회원 목록 페이지로 리다이렉트
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 무효화
        return "redirect:/customer/";  // 인덱스 페이지의 HTML 파일명 리턴
    }

    @PostMapping("/member/email-check")
    //ajax 쓸땐 @ResponseBody로 함
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);  // 이메일 중복 체크
        return checkResult;  // 중복 여부에 따른 결과 리턴
    }
}
