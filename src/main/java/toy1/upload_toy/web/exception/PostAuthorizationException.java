package toy1.upload_toy.web.exception;

/**
 * 비인가된 사용자가 게시글을 작성하려 할 때 발성하는 예외
 */
public class PostAuthorizationException extends RuntimeException {
    public PostAuthorizationException() {
        super();
    }

    public PostAuthorizationException(String message) {
        super(message);
    }

    public PostAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostAuthorizationException(Throwable cause) {
        super(cause);
    }
}
