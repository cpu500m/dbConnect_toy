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
        memberJpaRepository.save(Member.createMember("test111", "test111","테스트1"));
        memberJpaRepository.save(Member.createMember("test222", "test222","테스트2"));

        // 포스트
        itemJpaRepository.save(Item.createItem("안녕하세요!", "테스터1", "안녕하세요 반갑습니다!"));
        itemJpaRepository.save(Item.createItem("안녕!", "테스터2", "안녕 반가워~~~!"));
    }
}
