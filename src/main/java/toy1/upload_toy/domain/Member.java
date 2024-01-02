package toy1.upload_toy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    private Long memberId;

    private String loginId;
    private String password;
    private String nickName;

    @Builder
    private Member(String loginId, String password, String nickName) {
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
    }


    // 원래는 아래 메서드로 엔티티를 생성했었는데,
    // 이는 도메인 변경에 너무 민감해서 builder 패턴을 쓰기로 결정.
/*   public static Member createMember(String id, String password, String nickName) {
        Member member= new Member();
        member.loginId = id;
        member.password = password;
        member.nickName = nickName;
        return member;
    }*/
}
