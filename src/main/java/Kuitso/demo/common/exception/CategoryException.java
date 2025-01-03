package Kuitso.demo.common.exception;

import Kuitso.demo.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class CategoryException extends RuntimeException{
    private final ResponseStatus exceptionStatus;

    public CategoryException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public CategoryException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }
}