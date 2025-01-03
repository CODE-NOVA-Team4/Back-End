package Kuitso.demo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSLogInRequest {

    /**
     * 로그인 request dto
     */
    private String email;
    private String password;
}
