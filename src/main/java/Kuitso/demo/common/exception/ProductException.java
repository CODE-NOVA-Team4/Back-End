package Kuitso.demo.common.exception;

import Kuitso.demo.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class ProductException extends RuntimeException{
    private final ResponseStatus exceptionStatus;

    public ProductException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public ProductException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }
}
