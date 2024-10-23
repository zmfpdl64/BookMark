package woojin.bookmaker.handler.service;

public class CustomException extends RuntimeException{
    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public Integer getStatus() {
        return errorCode.getStatus();
    }
}
