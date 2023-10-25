package toy1.upload_toy.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import toy1.upload_toy.domain.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = new MemberRepository();
    @Test
    public void 저장테스트() throws Exception{
        //given
        Member member1 = memberRepository.save(Member.createMember("test111", "test111", "테스터1"));
        Member member2 = memberRepository.save(Member.createMember("test222", "test222", "테스터2"));
        //when
        assertThat(memberRepository.findAll()).containsExactly(member1, member2);

        //then
    }

    @Test
    public void 조회테스트() throws Exception{
        //given
        Member member1 = memberRepository.save(Member.createMember("test111", "test111", "테스터1"));
        //when
        Member findMember = memberRepository.findByLoginId("test111").orElse(null);
        //then
        assertThat(findMember).isEqualTo(member1);
    }

}