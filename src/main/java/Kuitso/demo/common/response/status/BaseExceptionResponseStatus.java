package Kuitso.demo.common.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus{


    /**
     * 200: 요청 성공 (OK)
     */
    SUCCESS(200,HttpStatus.OK.value(), "요청에 성공하였습니다."),
    FAILURE(400, HttpStatus.BAD_REQUEST.value(), "요청에 실패하였습니다."),

    /**
     * 2000: 유저관련
     */
    CANNOT_FOUND_USER(2000, HttpStatus.BAD_REQUEST.value(), "유저를 찾을수 없습니다"),
    ALREADY_EXIST_USER(2001, HttpStatus.BAD_REQUEST.value(), "이미등록된 회원입니다"),
    LOGIN_FAILED(2002, HttpStatus.BAD_REQUEST.value(), "로그인에 실패했습니다"),
    CANNOT_FOUND_SESSION(2002, HttpStatus.BAD_REQUEST.value(), "세션을 찾을수없습니다"),

    /**
     * 3000: 카테고리관련
     */
    CANNOT_FOUND_CATEGORY(2000, HttpStatus.BAD_REQUEST.value(), "해당하는 카테고리가 없습니다");

    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
