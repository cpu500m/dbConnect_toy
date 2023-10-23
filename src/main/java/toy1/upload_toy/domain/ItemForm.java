package toy1.upload_toy.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {
    private Long itemId;

    @Size(min = 2, max = 50)
    private String title;
    @NotBlank
    private String writer;
    @Size(min = 10, max = 1000)
    private String text;
    private List<MultipartFile> imageFiles;
    private MultipartFile attachFile;
}
