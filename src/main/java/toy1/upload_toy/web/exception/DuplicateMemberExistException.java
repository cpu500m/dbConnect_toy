package toy1.upload_toy.web.exception;

/**
 * 저장소에 동일한 ID의 회원이 이미 존재 시 발생하는 예외
 */
public class DuplicateMemberExistException extends RuntimeException {
    public DuplicateMemberExistException() {
        super();
    }

    public DuplicateMemberExistException(String message) {
        super(message);
    }

    public DuplicateMemberExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateMemberExistException(Throwable cause) {
        super(cause);
    }
}
