package woojin.bookmaker.service.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CategoryErrorCode implements ErrorCode{
    NOT_EXISTS(HttpStatus.BAD_REQUEST.value(), "카테고리가 존재하지 않습니다."),
    NOT_AUTHORIZATION(HttpStatus.FORBIDDEN.value(), "유저의 권한이 없습니다.");
    private final int status;
    private final String message;

    @Override
    public Integer getStatus() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
