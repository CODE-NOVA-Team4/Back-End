package Kuitso.demo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCodeRequest {

    /**
     * 이메일인증전송 request dto
     */
    private String email;
    private String univName;
    private Boolean univ_check;
}
