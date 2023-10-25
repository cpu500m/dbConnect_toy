package toy1.upload_toy.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import toy1.upload_toy.domain.Item;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.domain.Post;
import toy1.upload_toy.repository.ItemRepository;
import toy1.upload_toy.repository.MemberRepository;
import toy1.upload_toy.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class TestDataConfig {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ItemRepository itemRepository;

    /**
     * 테스트용 유저 등록
     */
    @PostConstruct
    public void init() {
        // 테스트 유저
        memberRepository.save(Member.createMember("test111", "test111","테스트1"));
        memberRepository.save(Member.createMember("test222", "test222","테스트2"));

        // 포스트
        postRepository.save(Post.createPost("안녕하세요!", "테스터1"));
        postRepository.save(Post.createPost("안녕!", "테스터2"));
        itemRepository.save(Item.createItem("안녕하세요!", "테스터1", "안녕하세요 반갑습니다!", null, null));
        itemRepository.save(Item.createItem("안녕!", "테스터2", "안녕 반가워~~~!", null, null));
    }
}
