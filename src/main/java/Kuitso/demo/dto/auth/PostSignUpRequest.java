package Kuitso.demo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSignUpRequest {

    /**
     * 회원가입 request dto
     */
    private String nickname;
    private String department;
    private String email;
    private String password;
}
