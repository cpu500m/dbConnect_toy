package toy1.upload_toy.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import toy1.upload_toy.domain.UploadFile;

import java.util.List;

/**
 * Item과 완전히 같은데 만든이유는
 * Controller에서 Item을 그대로 사용했을 때
 * 만약 필드를 실수로 수정하게 된다면
 * 영속성 컨텍스트 변경감지를 통해 DB 내의 값이 바뀌게 되므로
 * 그런 일을 막고자 만들었음.
 * 원래는 DTO를 사용하는 목적이
 * 1. 필요한 필드만 사용하려고
 * 2. entity 외부 노출을 피하려고
 * 두개만 생각 했었는데 코드 리팩토링하면서 한게 더생겻다..
 *
 * 3. 엔티티 불변을 유지하려고 (실수로 인해 변경감지를 통한 수정을 방지하려고)
 */
@Data
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long itemId;
    private String title;
    private String writer;
    private String text;

    private List<UploadFile> imageFiles;

}
