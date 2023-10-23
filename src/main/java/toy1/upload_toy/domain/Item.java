package toy1.upload_toy.domain;

import lombok.Getter;
import lombok.Setter;
import toy1.upload_toy.file.UploadFile;

import java.util.List;

/**
 * Spring에서 제공하는 MultipartFile interface로 바이너리를 받아서 게시물 출력
 * 간단하게 메모리에 저장하는 형태로 할가러 setter 열었음
 */
@Getter @Setter
public class Item {
    private Long itemId;
    private String title;
    private String writer;
    private String text;
    private List<UploadFile> imageFiles;
    private UploadFile attachFile;

    // 생성자를 통해 생성하는 것이 아닌 메서드를 통해 생성하게 하고자 private으로 두었음.
    // 향후 JPA 기술을 접목하면 protected로 바꾸던가 아니면 엔티티 구조가 바뀌던가 할듯.
    private Item(){

    }

    public static Item createItem(String title, String writer, String text,
                           List<UploadFile> imageFiles, UploadFile file) {
        Item item = new Item();
        item.title = title;
        item.writer = writer;
        item.text = text;
        item.imageFiles = imageFiles;
        item.attachFile = file;

        return item;
    }

}
