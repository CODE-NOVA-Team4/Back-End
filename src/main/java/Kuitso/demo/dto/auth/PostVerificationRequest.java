package Kuitso.demo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostVerificationRequest {

    /**
     * 이메일인증확인 request dto
     */
    private String email;
    private String code;
}
