package woojin.bookmaker.adaptor.service.bookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import woojin.bookmaker.adaptor.service.ErrorCode;


@RequiredArgsConstructor
public enum BookmarkErrorCode implements ErrorCode {
    NOT_EXISTS(HttpStatus.BAD_REQUEST.value(), "북마크가 존재하지 않습니다."),
    UNAUTHORIZATION(HttpStatus.UNAUTHORIZED.value(), "권한이 존재하지 않습니다.");
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
