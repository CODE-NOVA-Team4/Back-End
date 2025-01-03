package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.dto.auth.PostSLogInRequest;
import Kuitso.demo.dto.auth.PostSLogInResponse;
import Kuitso.demo.dto.category.GetCategoryRequest;
import Kuitso.demo.dto.category.GetCategoryResponse;
import Kuitso.demo.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public BaseResponse<GetCategoryResponse> info(@PathVariable long categoryId,
                                                      @RequestBody GetCategoryRequest getCategoryRequest) {

        log.info("[CategoryController].info");
        return new BaseResponse<>(categoryService.info(categoryId,getCategoryRequest));
    }


}
