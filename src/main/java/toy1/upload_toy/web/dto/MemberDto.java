package toy1.upload_toy.web.dto;



public class MemberDto {

    private String loginId;

    private String password;

    private MemberDto(){

    }

    public static MemberDto createMemberDto(String id, String password) {
        MemberDto dto= new MemberDto();
        dto.loginId = id;
        dto.password = password;
        return dto;
    }
}
