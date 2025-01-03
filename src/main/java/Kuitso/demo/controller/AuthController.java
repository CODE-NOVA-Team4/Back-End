package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.dto.auth.PostSignUpRequest;
import Kuitso.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public BaseResponse<Void> signup(@RequestParam PostSignUpRequest postSignUpRequest) {

        log.info("[AuthController].signup");
        authService.signup(postSignUpRequest);
        return  new BaseResponse<>(null);
    }
}
