package toy1.upload_toy;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import toy1.upload_toy.repository.MemberRepository;

@SpringBootApplication
public class UploadToyApplication {
	/**
	 * 해야 할 일
	 * 우선 properties 파일 yml로 바꾸자.. yml이 보기 좋다 ( 보안에 민감한 정보는 따로 뺴서 gitignore 등록하자 ) -- O
	 * 1 . Post랑 Item이 굳이 분리돼 있을 이유가 없어 보니깐. 걍 Item만을 Domain Entity로 쓰고
	 *  Poist는 Dto로 떼서 쓰자
	 *  2. UploadFile같은 경우는 Entity로 승격시켜서 쓰자.
	 *  3 . JPA Repository 만들자 ( Spring Data 이용해서 하자)
	 */

	public static void main(String[] args) {
		SpringApplication.run(UploadToyApplication.class, args);
	}

}
