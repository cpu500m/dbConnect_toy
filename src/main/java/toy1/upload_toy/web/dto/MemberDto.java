package toy1.upload_toy.web.dto;


import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {

    @Size(min = 7 , max = 30)
    private String loginId;
    @Size(min = 7 , max = 30)

    private String password;
    @Size(min = 2 , max = 15)

    private String nickName;
}
