package toy1.upload_toy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.repository.MemberRepository;
import toy1.upload_toy.web.exception.DuplicateMemberExistException;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginService {
    private final MemberRepository memberRepository;


    // 회원 가입
    public Member save(Member member) {
        Member duplicateMember = memberRepository.findAll().stream()
                .filter(m -> m.getLoginId().equals(member.getLoginId()))
                .findAny().orElse(null);
        if (duplicateMember != null) {
            log.debug("회원가입 예외. 아이디 동일 회원 존재.");
            throw new DuplicateMemberExistException("아이디가 동일한 회원 존재.");
        }

        return memberRepository.save(member);
    }


    // repository 에서 loginId로 가져왔으니 패스워드 대조해봐야 함.
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }

}
