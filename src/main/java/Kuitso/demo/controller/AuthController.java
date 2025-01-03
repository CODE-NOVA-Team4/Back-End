package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.common.response.status.BaseExceptionResponseStatus;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.auth.*;
import Kuitso.demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public BaseResponse<Void> signup(@RequestBody PostSignUpRequest postSignUpRequest, HttpServletRequest request) {

        log.info("[AuthController].signup");
        authService.signup(postSignUpRequest,request);
        return new BaseResponse<>(null);
    }

    @PatchMapping("/signout")
    public BaseResponse<Void> signout(HttpServletRequest request) {

        log.info("[AuthController].signout");
        authService.signout(request);
        return new BaseResponse<>(null);
    }

    @PostMapping("/login")
    public BaseResponse<PostSLogInResponse> login(@RequestBody PostSLogInRequest postSLogInRequest,HttpServletRequest request) {

        log.info("[AuthController].login");
        return new BaseResponse<>(authService.login(postSLogInRequest,request));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(HttpServletRequest request) {
        log.info("[AuthController].logout");
        authService.logout(request);
        return new BaseResponse<>(null);
    }

    @PostMapping("/code")
    public BaseResponse<Boolean> code(@RequestBody PostCodeRequest postCodeRequest) throws IOException {
        log.info("[AuthController].code");
        if(authService.sendCode(postCodeRequest)) {
            return new BaseResponse<>(BaseExceptionResponseStatus.SUCCESS, Boolean.TRUE);
        }
        return new BaseResponse<>(BaseExceptionResponseStatus.FAILURE, Boolean.TRUE);
    }

    @PostMapping("/verification")
    public BaseResponse<PostVerificationResponse> verify(@RequestBody PostVerificationRequest postVerificationRequest) throws IOException {

        log.info("[AuthController].verification");
        return new BaseResponse<>(authService.verification(postVerificationRequest));
    }

}
