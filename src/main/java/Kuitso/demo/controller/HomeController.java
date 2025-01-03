package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.dto.HomeResponse;
import Kuitso.demo.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor

public class HomeController {

    private final HomeService homeService;

    @GetMapping("/home")
    public BaseResponse<HomeResponse> getHomeData(HttpServletRequest request) {
        return new BaseResponse<>(homeService.getHomeData(request));
    }

}
