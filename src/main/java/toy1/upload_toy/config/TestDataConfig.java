package toy1.upload_toy.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class TestDataConfig {
    private final MemberRepository memberRepository;
    /**
     * 테스트용 유저 등록
     */
    @PostConstruct
    public void init() {
        memberRepository.save(Member.createMember("test111", "test111","테스트1"));
        memberRepository.save(Member.createMember("test222", "test222","테스트2"));
    }
}
