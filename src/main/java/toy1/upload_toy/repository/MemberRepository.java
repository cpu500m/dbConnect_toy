package toy1.upload_toy.repository;

import org.springframework.stereotype.Repository;
import toy1.upload_toy.domain.Member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private long sequence = 0L;

    public Member save(Member member) {
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
        return member;
    }

}
