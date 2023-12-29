package toy1.upload_toy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 홈에서 게시물 리스트 보여주기 위함
 */
@Entity
@Getter @Setter
public class Post {
    @GeneratedValue @Id
    private Long postId;
    private String title;
    private String writer;

    protected Post(){}

    public static Post createPost(String title ,String writer) {
        Post post = new Post();
        post.title = title;
        post.writer = writer;
        return post;
    }
}
