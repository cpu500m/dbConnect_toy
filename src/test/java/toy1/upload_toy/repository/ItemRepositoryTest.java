package toy1.upload_toy.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import toy1.upload_toy.domain.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    @Test
    public void 저장테스트() throws Exception{
        //given
        Item save1 = itemRepository.save(Item.createItem("1", "나1", "대충쓰자ㅁㅁㅁㅁㅁㅁㅁ", null, null));
        Item save2 = itemRepository.save(Item.createItem("2", "너2", "대충쓰자ㅁㅁㅁㅁㅁㅁㅁ", null, null));

        //when
        Assertions.assertThat(itemRepository.findById(save1.getItemId())).isEqualTo(save1);
        Assertions.assertThat(itemRepository.findById(save2.getItemId())).isEqualTo(save2);

    }
}