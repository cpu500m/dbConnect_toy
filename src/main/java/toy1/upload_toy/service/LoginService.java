package toy1.upload_toy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.repository.MemberJpaRepository;
import toy1.upload_toy.web.dto.MemberDto;
import toy1.upload_toy.web.exception.DuplicateMemberExistException;
import toy1.upload_toy.web.util.DtoUtils;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class LoginService {
    private final MemberJpaRepository memberJpaRepository;


    // 회원 가입
    public void save(MemberDto memberDto) {
        Member duplicateMember = memberJpaRepository.findAll().stream()
                .filter(m -> m.getLoginId().equals(memberDto.getLoginId()))
                .findAny().orElse(null);
        if (duplicateMember != null) {
            log.debug("회원가입 예외. 아이디 동일 회원 존재.");
            throw new DuplicateMemberExistException("아이디가 동일한 회원 존재.");
        }

        memberJpaRepository.save(DtoUtils.memberDtoToMember(memberDto));
    }


    // repository 에서 loginId로 가져왔으니 패스워드 대조해봐야 함.
    public String login(String loginId, String password) {
        Member findMember = memberJpaRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
        if(findMember != null){
            return findMember.getNickName();
        }
        return null;
    }

}
