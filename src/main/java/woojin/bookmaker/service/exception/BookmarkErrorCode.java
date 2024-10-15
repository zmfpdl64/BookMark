package woojin.bookmaker.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
public enum BookmarkErrorCode implements ErrorCode{
    NOT_EXISTS(HttpStatus.BAD_REQUEST.value(), "북마크가 존재하지 않습니다.");
    private final int status;
    private final String message;

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
