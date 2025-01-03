package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.dto.auth.PostSignUpRequest;
import Kuitso.demo.dto.product.GetProductSearchFilterRequest;
import Kuitso.demo.dto.product.GetProductSearchRequest;
import Kuitso.demo.dto.product.GetProductSearchResponse;
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

    @GetMapping("/search")
    public BaseResponse<GetProductSearchResponse> search(@RequestBody GetProductSearchRequest getProductSearchRequest) {

        log.info("[ProductController].search");
        return new BaseResponse<>(productService.search(getProductSearchRequest));
    }

//    @GetMapping("/list")
//    public BaseResponse<GetProductSearchFilterRequest> searchFilter(@RequestParam long categoryId,@RequestBody GetProductSearchFilterRequest getProductSearchFilterRequest) {
//
//        log.info("[ProductController].searchFilter");
//        return new BaseResponse<>(productService.searchFilter(categoryId,getProductSearchFilterRequest));
//    }
}
