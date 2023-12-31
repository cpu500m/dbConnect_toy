package toy1.upload_toy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id @GeneratedValue
    private Long memberId;

    @Size(min = 7 , max = 30)
    private String loginId;
    @Size(min = 7 , max = 30)
    private String password;
    @Size(min = 2 , max = 15)

    private String nickName;

    private Member(){

    }

    public static Member createMember(String id, String password, String nickName) {
        Member member= new Member();
        member.loginId = id;
        member.password = password;
        member.nickName = nickName;
        return member;
    }
}
