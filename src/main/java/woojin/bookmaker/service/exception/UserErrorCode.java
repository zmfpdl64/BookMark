package woojin.bookmaker.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{
    EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST.value(), "이메일이 중복됩니다.");

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
