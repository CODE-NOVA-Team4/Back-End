package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.dto.product.GetProductSearchInfoResponse;
import Kuitso.demo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import Kuitso.demo.dto.auth.PostSignUpRequest;
import Kuitso.demo.dto.product.*;
import Kuitso.demo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{productId}/wish-register")
    public BaseResponse<Void> registerWish(@PathVariable String productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");

        productService.addWishProduct(userId, Long.valueOf(productId));

        return new BaseResponse<>(null);
    }

    @GetMapping("/{productId}/info")
    public BaseResponse<GetProductSearchInfoResponse> searchProductInfo(@PathVariable Long productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        return new BaseResponse<>(productService.getProductInfoById(productId, userId));
    }

    @GetMapping("/search")
    public BaseResponse<GetProductSearchResponse> search(@RequestBody GetProductSearchRequest getProductSearchRequest) {

        log.info("[ProductController].search");
        return new BaseResponse<>(productService.search(getProductSearchRequest));
    }

    @PostMapping("/register")
    public BaseResponse<Void> register(@RequestBody PostProductRegisterRequest postProductRegisterRequest,HttpServletRequest httpServletRequest) {

        log.info("[ProductController].register");
        return new BaseResponse<>(productService.register(postProductRegisterRequest,httpServletRequest));
    }

//    @GetMapping("/list")
//    public BaseResponse<GetProductSearchFilterResponse> searchFilter(@RequestParam long categoryId,
//                                                                     @RequestBody GetProductSearchFilterRequest getProductSearchFilterRequest) {
//
//        log.info("[ProductController].searchFilter");
//        return new BaseResponse<>(productService.searchFilter(categoryId,getProductSearchFilterRequest));
//    }


}
