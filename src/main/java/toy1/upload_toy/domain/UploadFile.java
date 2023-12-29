package toy1.upload_toy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFile {
    // 사용자가 올린 이름
    private String uploadName;

    // 저장 경로 + UUID
    private String storeName;
}
