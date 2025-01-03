package Kuitso.demo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostVerificationResponse {

    /**
     * 이메일인증확인 response dto
     */
    private boolean success;
    private String univName;
    private String certified_email;
    private LocalDateTime certified_date;
}
