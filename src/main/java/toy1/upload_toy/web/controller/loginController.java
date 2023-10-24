package toy1.upload_toy.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import toy1.upload_toy.repository.MemberRepository;
import toy1.upload_toy.web.dto.MemberDto;

@Controller
@RequiredArgsConstructor
public class loginController {
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto) {
        return "login/loginForm";
    }
}
