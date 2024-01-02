package toy1.upload_toy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 저장
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    private Long itemId;
    private String title;
    private String writer;
    private String text;

    // Item의 생명주기와 맞추게. Aggregate root로 관리
    // getter 수동 등록
    // 별도의 로직 처리를 하는 경우는 없기 때문에 일급 컬렉션을 쓰지 않았음.
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST ,orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    private List<UploadFile> imageFiles = new ArrayList<>();

    @Builder
    private Item(String title, String writer, String text){
        this.title = title;
        this.writer = writer;
        this.text = text;
    }

    public void setImageFiles(List<UploadFile> uploadFiles){
        // 불변을 보장하면서
        // 기존 컬렉션을 Hibernate에서 내부적으로  감싸기 때문에
        // 이를 지키면서 코드 작성하기위해 아래처럼 했음.
        // 중복 체크 로직이 원래라면 필요하겠지만 뭐 이경우는 .. 굳이니깐
        for (UploadFile uploadFile : uploadFiles) {
            uploadFile.setItem(this);
            imageFiles.add(uploadFile);
        }
    }


    /**
     * imageFiles getter (불변 위해)
     */
    // 근데 이게 LIst가 아니라 어차피 전체 출력을 할건데
    // Iterator를 반환하는편이 좀 더 좋아보이긴함. 이건 마지막에 한번 더 생각해보자고
    // 라고 생각을 했었는데.. 컨트롤러에서 기존에 Item을 직접 넘기던점이 약간 찜찜했음.
    // 나는 서비스 레벨에서만 엔티티 변경을 허용하고 싶었기 떄문에..
    // 그래서 Dto로 변환도 고려해야해서 List로 반환할 이유가 생겼음.
    public  List<UploadFile> getImageFiles(){
        return new ArrayList<>(imageFiles);
    }

    /**
     * 제목 변경
     */
    public void changeTitle(String title){
        this.title = title;
    }

    /**
     * 내용 변경
     */
    public void changeText(String text){
        this.text = text;
    }

    // 원래는 아래 메서드로 엔티티를 생성했었는데,
    // 이는 도메인 변경에 너무 민감해서 builder 패턴을 쓰기로 결정.
    /*public static Item createItem(String title, String writer, String text, List<UploadFile> imageFiles) {
        Item item = new Item();
        item.title = title;
        item.writer = writer;
        item.text = text;

        // 도메인 영역 불변을 위해 새로 list 생성 후 할당
        item.imageFiles = new ArrayList<>(imageFiles);

        return item;
    }*/
}
