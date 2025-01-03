package Kuitso.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserInfoResponse {

    /**
     * 회원정보설정 response dto
     */
    private String nickname;
    private String department;
    private String email;
    private String password;
}
