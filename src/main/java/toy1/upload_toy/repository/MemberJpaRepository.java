package toy1.upload_toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy1.upload_toy.domain.Member;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
}
