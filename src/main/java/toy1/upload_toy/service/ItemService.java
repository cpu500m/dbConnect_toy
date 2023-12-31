package toy1.upload_toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy1.upload_toy.domain.Item;
import toy1.upload_toy.repository.ItemJpaRepository;
import toy1.upload_toy.web.dto.ItemForm;
import toy1.upload_toy.web.dto.Post;
import toy1.upload_toy.web.util.DtoUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private ItemJpaRepository itemJpaRepository;

    public List<Post> findAll() {
        List<Item> items = itemJpaRepository.findAll();
        return items.stream()
                .map(i -> DtoUtils.itemToPost(i))
                .toList();
    }

    /**
     * item 저장 후 id를 반환.
     */
    public Long saveItem(ItemForm itemForm) {
        Item item = DtoUtils.itemFormToItem(itemForm);
        Item savedItem = itemJpaRepository.save(item);
        return savedItem.getItemId();
    }

    /**
     * item찾고 Dto로 변환하여 리턴
     */
    public Item findItem(Long itemId) {
        return itemJpaRepository.findById(itemId).orElse(null);
    }
}
