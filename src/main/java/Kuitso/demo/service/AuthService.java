package Kuitso.demo.service;

import Kuitso.demo.dto.auth.PostSignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    public void signup(PostSignUpRequest postSignUpRequest) {
        String email = postSignUpRequest.getEmail();
        String password = postSignUpRequest.getPassword();

        String department = postSignUpRequest.getDepartment();

        ;

    }
}
