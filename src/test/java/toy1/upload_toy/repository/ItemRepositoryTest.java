package toy1.upload_toy.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy1.upload_toy.domain.Item;


@SpringBootTest

class ItemRepositoryTest {
    @Autowired
    private ItemJpaRepository itemRepository;
    @Test
    public void 저장테스트() throws Exception{
        //given
        Item save1 = itemRepository.save(Item.builder()
                .title("1")
                .writer("너1")
                .text("대충대충~~!")
                .build());
        Item save2 = itemRepository.save(Item.builder()
                .title("2")
                .writer("너2")
                .text("대충대충~123133~!")
                .build());

        //when
        Assertions.assertThat(itemRepository.findById(save1.getItemId())).isEqualTo(save1);
        Assertions.assertThat(itemRepository.findById(save2.getItemId())).isEqualTo(save2);

    }
}