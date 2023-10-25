package toy1.upload_toy.web.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    @Size(min = 7 , max = 30)
    private String loginId;
    @Size(min = 7 , max = 30)

    private String password;
    @Size(min = 2 , max = 15)

    private String nickName;

    private MemberDto(){

    }

    public static MemberDto createMemberDto(String id, String password, String nickName) {
        MemberDto dto= new MemberDto();
        dto.loginId = id;
        dto.password = password;
        dto.nickName = nickName;
        return dto;
    }
}
