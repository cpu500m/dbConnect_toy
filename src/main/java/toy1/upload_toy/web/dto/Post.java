package toy1.upload_toy.web.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 홈에서 게시물 리스트 보여주기 위함
 */
@Data
@Builder
public class Post {

    private Long postId;
    private String title;
    private String writer;
}
