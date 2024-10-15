package woojin.bookmaker.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST.value(), "이메일이 중복됩니다."),
    NOT_EXISTS(HttpStatus.UNAUTHORIZED.value(), "유저가 존재하지 않습니다."),
    NOT_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), "인증정보가 일치하지 않습니다.");

    private final Integer status;
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
