package toy1.upload_toy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 값 타입인데 Entity로 승격시켜 사용 ( Item에서 다 관계로 참조하므로 최적화를 위해)
 */
@Getter
@AllArgsConstructor
@Entity
public class UploadFile {
    @Id @GeneratedValue
    private Long uploadFileId;
    // 사용자가 올린 이름
    private String uploadName;

    // 저장 경로 + UUID
    private String storeName;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public UploadFile(String uploadName, String storeName) {
        this.uploadName = uploadName;
        this.storeName = storeName;
    }
}
