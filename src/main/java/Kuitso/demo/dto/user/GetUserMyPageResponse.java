package Kuitso.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserMyPageResponse {

    /**
     * 마이페이지 response dto
     */
    private String nickname;
    private String department;
}
