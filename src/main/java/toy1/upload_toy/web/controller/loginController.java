package toy1.upload_toy.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.service.LoginService;
import toy1.upload_toy.web.dto.MemberDto;
import toy1.upload_toy.web.exception.DuplicateMemberExistException;
import toy1.upload_toy.web.util.DtoUtils;

import static toy1.upload_toy.web.session.SessionConst.LOGIN_IDENTIFIER;

@Controller
@Slf4j
@RequiredArgsConstructor
public class loginController {
    private final LoginService loginService;

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginPost(@Valid @ModelAttribute MemberDto memberDto,
                            BindingResult bindingResult , HttpServletRequest request,
                            @RequestParam(defaultValue = "/") String redirectURL){

        // bean validation 걸릴 때
        if (bindingResult.hasErrors()) {
            log.debug("error = {}", bindingResult);
            return "login/loginForm";
        }

        // 로그인 실패한 경우
        String nickName = loginService.login(memberDto.getLoginId()
                , memberDto.getPassword());
        if (nickName == null) {
            bindingResult.reject("loginFail");
            return "login/loginForm";
        }

        // 성공한 경우 닉네임 저장
        memberDto.setNickName(nickName);

        // 성공한 경우. 세션 생성 + 기존 요청 주소로 리다이렉트
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_IDENTIFIER, memberDto);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /**
     * 회원 가입
     */
    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute MemberDto member) {
        return "login/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute MemberDto member,
                         BindingResult bindingResult , Model model) {

        // bean validation 걸린 경우 ( 7~30 자 만족 안할 때)
        if (bindingResult.hasErrors()) {
            log.debug("error = {}", bindingResult);
            return "login/signUp";
        }

        // 저장소에서 id가 같은 회원 있는지 조회.
        try {
            loginService.save(member);
        } catch (DuplicateMemberExistException e) {
            bindingResult.reject("signUpFail");
            return "login/signUp";
        }

        // 성공시 . 로그인 화면으로 보냄
        return "redirect:/login";
    }
}
