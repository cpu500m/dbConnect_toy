package toy1.upload_toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy1.upload_toy.domain.Item;

public interface ItemJpaRepository extends JpaRepository<Item,Long> {

}
