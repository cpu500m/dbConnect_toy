package toy1.upload_toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy1.upload_toy.domain.Item;
import toy1.upload_toy.domain.UploadFile;
import toy1.upload_toy.repository.ItemJpaRepository;
import toy1.upload_toy.web.dto.ItemForm;
import toy1.upload_toy.web.dto.Post;
import toy1.upload_toy.web.util.DtoUtils;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private ItemJpaRepository itemJpaRepository;
    private FileService fileService;

    public List<Post> findAll() {
        List<Item> items = itemJpaRepository.findAll();
        return items.stream()
                .map(i -> DtoUtils.itemToPost(i))
                .toList();
    }

    /**
     * item 저장 후 id를 반환.
     */
    public Long saveItem(ItemForm itemForm) throws IOException {
        Item item = DtoUtils.itemFormToItem(itemForm);

        // 서버 저장소에 파일 저장
        List<UploadFile> uploadFiles = fileService.storeFiles(itemForm.getImageFiles());
        Item savedItem = itemJpaRepository.save(item);
        return savedItem.getItemId();
    }

    /**
     * item찾아서 반환
     */
    public Item findItem(Long itemId) {
        return itemJpaRepository.findById(itemId)
                .orElse(null);
    }
}
