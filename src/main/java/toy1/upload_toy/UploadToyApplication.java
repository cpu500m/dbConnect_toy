package toy1.upload_toy;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import toy1.upload_toy.repository.MemberRepository;

@SpringBootApplication
public class UploadToyApplication {

	/* TODO
	* -  redirectURL 처리하기				--- O
	* - 로그인 페이지 , 회원가입 페이지 만들기			--- O
	* - 글 작성할 때 작성자명 로그인한 회원 id로 자동 기입되도록 수정하기 --- O
	* - 회원가입 관련 bean validation 적용하기 ---	O
	* - 로그인 할 때 로그인 실패 시 object error 띄우기. ---	O
	* - directory 구조 개선 및 코드 리팩터링 ( 다 하고 )
	* */
	public static void main(String[] args) {
		SpringApplication.run(UploadToyApplication.class, args);
	}

}
