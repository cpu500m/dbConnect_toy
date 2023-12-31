package toy1.upload_toy.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

/**
 * 게시글 저장
 */
@Getter
@Entity
public class Item {
    @Id @GeneratedValue
    private Long itemId;
    private String title;
    private String writer;
    private String text;

    // Item의 생명주기와 맞추게. Aggregate root로 관리
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST ,orphanRemoval = true)
    private List<UploadFile> imageFiles;

    protected Item(){

    }

    public static Item createItem(String title, String writer, String text) {
        Item item = new Item();
        item.title = title;
        item.writer = writer;
        item.text = text;

        return item;
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
}
