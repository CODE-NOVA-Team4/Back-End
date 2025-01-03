package Kuitso.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserInfoRequest {

    /**
     * 회원정보수정 request dto
     */
    private String nickname;
    private String department;
    private String password;
}
