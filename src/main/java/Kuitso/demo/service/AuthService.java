package Kuitso.demo.service;

import Kuitso.demo.common.exception.UserException;
import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.auth.*;
import Kuitso.demo.repository.UserRepository;
import com.univcert.api.UnivCert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static Kuitso.demo.common.response.status.BaseExceptionResponseStatus.*;
import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;
import static Kuitso.demo.domain.base.BaseStatus.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
//    @Value("{univcert.api.key")
    private String apiKey = "2a4efd85-c695-4dc9-bffe-f0dab0a15363";

    public void signup(PostSignUpRequest postSignUpRequest, HttpServletRequest request) {

        String nickname = postSignUpRequest.getNickname();
        String email = postSignUpRequest.getEmail();
        String password = postSignUpRequest.getPassword();
        String department = postSignUpRequest.getDepartment();

        //이미 존재하는 회원 예외처리
        if (userRepository.existsByEmailAndStatus(email, ACTIVE)) {
            throw new UserException(ALREADY_EXIST_USER);
        } else {

            //데이터베이스에 저장
            User user = new User(nickname, email, password, department);
            userRepository.save(user);

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());

        }

    }

    public void signout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {

            Long userId = (Long) (session.getAttribute("userId"));

            User user = userRepository.findByUserIdAndStatus(userId, ACTIVE)
                    .orElseThrow(() -> new UserException(CANNOT_FOUND_USER));

            //세션 무효화
            session.removeAttribute("userId");

            //유저상태변경
            user.setStatus(DELETED);

            userRepository.save(user);

        } else throw new UserException(CANNOT_FOUND_SESSION);

    }

    public PostSLogInResponse login(PostSLogInRequest postSLogInRequest, HttpServletRequest request) {
        String email = postSLogInRequest.getEmail();
        String password = postSLogInRequest.getPassword();

        User user = userRepository.findByEmailAndStatus(email, ACTIVE)
                .orElseThrow(() -> new UserException(CANNOT_FOUND_USER));

        if (user.getPassword().equals(password)) {
            log.info("Login successful");
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());

        } else throw new UserException(LOGIN_FAILED);

        return new PostSLogInResponse(user.getUserId());

    }

    public void logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {

            //세션 무효화
            request.getSession().removeAttribute("userId");
        } else throw new UserException(CANNOT_FOUND_SESSION);
    }

    public Boolean sendCode(PostCodeRequest postCodeRequest) throws IOException {
        String email = postCodeRequest.getEmail();
        UnivCert.clear(apiKey, email);

        Map<String, Object> result = UnivCert.certify(apiKey, email, "건국대학교", true);

        if (!(boolean) result.get("success")) {
            return false;
        } else {
            return true;
        }
    }

    public PostVerificationResponse verification(PostVerificationRequest postVerificationRequest) throws IOException {
        String email = postVerificationRequest.getEmail();
        int code = Integer.parseInt(postVerificationRequest.getCode());
        Map<String, Object> result = UnivCert.certifyCode(apiKey, email, "건국대학교", code);

        if ((boolean) result.get("success")) {
            return new PostVerificationResponse(true, "건국대학교", email, LocalDateTime.now());
        } else{
            return new PostVerificationResponse(false, "", "", LocalDateTime.now());
        }
    }
}
