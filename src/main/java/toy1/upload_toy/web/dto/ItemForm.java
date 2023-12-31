package toy1.upload_toy.web.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import toy1.upload_toy.domain.Item;

import java.util.List;

@Data
public class ItemForm {
    private Long id;

    @Size(min = 2, max = 50)
    private String title;
    private String writer;
    @Size(min = 10, max = 1000)
    private String text;
    private List<MultipartFile> imageFiles;

}
