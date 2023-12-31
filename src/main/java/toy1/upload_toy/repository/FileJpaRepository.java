package toy1.upload_toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy1.upload_toy.domain.UploadFile;

public interface FileJpaRepository extends JpaRepository<UploadFile,Long> {
}
