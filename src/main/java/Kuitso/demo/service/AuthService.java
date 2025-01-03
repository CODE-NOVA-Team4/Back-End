package Kuitso.demo.service;

import Kuitso.demo.common.exception.UserException;
import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.auth.PostSLogInRequest;
import Kuitso.demo.dto.auth.PostSLogInResponse;
import Kuitso.demo.dto.auth.PostSignUpRequest;
import Kuitso.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static Kuitso.demo.common.response.status.BaseExceptionResponseStatus.*;
import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;
import static Kuitso.demo.domain.base.BaseStatus.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void signup(PostSignUpRequest postSignUpRequest, HttpServletRequest request) {

        String nickname = postSignUpRequest.getNickname();
        String email = postSignUpRequest.getEmail();
        String password = postSignUpRequest.getPassword();
        String department = postSignUpRequest.getDepartment();

        //이미 존재하는 회원 예외처리
        if(userRepository.existsByEmailAndStatus(email,ACTIVE)){
            throw new UserException(ALREADY_EXIST_USER);
        }else{

            //데이터베이스에 저장
            User user = new User(nickname, email, password, department);
            userRepository.save(user);

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());

        }

    }

    public void signout(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getSession().getAttribute("userId").toString());

        User user = userRepository.findByUserIdAndStatus(userId,ACTIVE)
                .orElseThrow(() -> new UserException(CANNOT_FOUND_USER));

        //세션 무효화
        request.getSession().removeAttribute("userId");

        //유저상태변경
        user.setStatus(DELETED);

    }

    public PostSLogInResponse login(PostSLogInRequest postSLogInRequest, HttpServletRequest request) {
        String email = postSLogInRequest.getEmail();
        String password = postSLogInRequest.getPassword();

        User user = userRepository.findByEmailAndStatus(email,ACTIVE)
                .orElseThrow(() -> new UserException(CANNOT_FOUND_USER));

        if(user.getPassword().equals(password)){
            log.info("Login successful");
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
        }
        else throw new UserException(LOGIN_FAILED);

        return new PostSLogInResponse(user.getUserId());

    }
}
