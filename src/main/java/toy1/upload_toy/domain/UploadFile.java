package toy1.upload_toy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 값 타입인데 Entity로 승격시켜 사용 ( Item에서 다 관계로 참조하므로 최적화를 위해)
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 이거는 뭐 도메인 변할일이 없으니깐.. 빌더패턴 쓰면 오버헤드만 생겨서 굳이 쓸 이유 없겠다
    public UploadFile(String uploadName, String storeName) {
        this.uploadName = uploadName;
        this.storeName = storeName;
    }
    void setItem(Item item){
        this.item = item;
    }
}
