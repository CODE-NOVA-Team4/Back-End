package Kuitso.demo.common.exception_handler;

import Kuitso.demo.common.exception.CategoryException;
import Kuitso.demo.common.exception.ProductException;
import Kuitso.demo.common.response.BaseErrorResponse;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class CategoryExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryException.class)
    public BaseErrorResponse handle_MemberException(CategoryException e) {
        log.error("[handle_CategoryException]", e);
        return new BaseErrorResponse(e.getExceptionStatus(), e.getMessage());
    }
}
