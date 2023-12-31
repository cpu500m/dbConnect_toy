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
    public void 저장테스트() throws Exception{
        //given
        Member member1 = memberJpaRepository.save(Member.createMember("test111", "test111", "테스터1"));
        Member member2 = memberJpaRepository.save(Member.createMember("test222", "test222", "테스터2"));
        //when
        assertThat(memberJpaRepository.findAll()).containsExactly(member1, member2);

        //then
    }

    @Test
    public void 조회테스트() throws Exception{
        //given
        Member member1 = memberJpaRepository.save(Member.createMember("test111", "test111", "테스터1"));
        //when
        Member findMember = memberJpaRepository.findByLoginId("test111").orElse(null);
        //then
        assertThat(findMember).isEqualTo(member1);
    }

}