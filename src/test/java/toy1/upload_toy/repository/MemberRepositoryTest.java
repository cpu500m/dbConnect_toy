package toy1.upload_toy.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy1.upload_toy.domain.Member;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class MemberRepositoryTest {
    private final MemberJpaRepository memberJpaRepository;

    @Test
    public void 저장테스트() throws Exception {
        //given
        Member member1 = memberJpaRepository.save(Member.builder()
                .loginId("test111")
                .password("test111")
                .nickName("테스터1")
                .build());
        Member member2 = memberJpaRepository.save(Member.builder()
                .loginId("test222")
                .password("test222")
                .nickName("테스터2")
                .build());
        //when
        assertThat(memberJpaRepository.findAll()).containsExactly(member1, member2);

        //then
    }

    @Test
    public void 조회테스트() throws Exception {
        //given
        Member member1 = memberJpaRepository.save(     Member.builder()
                .loginId("test111")
                .password("test111")
                .nickName("테스터1")
                .build());
        //when
        Member findMember = memberJpaRepository.findByLoginId("test111").orElse(null);
        //then
        assertThat(findMember).isEqualTo(member1);
    }

}