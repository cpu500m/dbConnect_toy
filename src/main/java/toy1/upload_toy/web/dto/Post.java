package toy1.upload_toy.web.dto;

import lombok.Data;

/**
 * 홈에서 게시물 리스트 보여주기 위함
 */
@Data
public class Post {

    private Long id;
    private String title;
    private String writer;

    protected Post(){

    }


    public static Post createPost(Long id, String title , String writer) {
        Post post = new Post();
        post.id = id;
        post.title = title;
        post.writer = writer;
        return post;
    }
}
