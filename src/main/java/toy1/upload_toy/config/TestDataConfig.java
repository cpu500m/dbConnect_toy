package toy1.upload_toy.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy1.upload_toy.domain.Item;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.repository.ItemJpaRepository;
import toy1.upload_toy.repository.MemberJpaRepository;

@Component
@RequiredArgsConstructor
public class TestDataConfig {
    private final MemberJpaRepository memberJpaRepository;
    private final ItemJpaRepository itemJpaRepository;

    /**
     * 테스트용 유저 등록
     */
    @PostConstruct
    public void init() {
        // 테스트 유저
        memberJpaRepository.save(Member.builder()
                .loginId("test111")
                .password("test111")
                .nickName("테스터1")
                .build());
        memberJpaRepository.save(Member.builder()
                .loginId("test222")
                .password("test222")
                .nickName("테스터2")
                .build());

        // 게시물
        itemJpaRepository.save(Item.builder().
                title("안녕하세요!").
                writer("테스터1").
                text("안녕하세요 반갑습니다!").build());
        itemJpaRepository.save(Item.builder().
                title("안녕!!!").
                writer("테스터2").
                text("안녕 반갑다!").build());
    }
}
