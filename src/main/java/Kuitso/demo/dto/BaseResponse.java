package Kuitso.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse {

    /**
     * 성공,실패 response dto
     */

    private int status;
    private boolean success;
    private String message;
}
