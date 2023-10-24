package toy1.upload_toy.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

// 일단 DB연동을 안헀으니 setter를 열어놨음.
// DB연동 후 memberId는 autoIncrement 전략 사용 예정
@Getter @Setter
public class Member {
    private Long memberId;

    @Range(min = 7 , max = 30)
    private String loginId;
    @Range(min = 7 , max = 30)
    private String password;

    private Member(){

    }

    public Member createMember(String id, String password) {
        Member member= new Member();
        member.loginId = id;
        member.password = password;
        return member;
    }
}
