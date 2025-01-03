package Kuitso.demo.service;

import Kuitso.demo.common.exception.UserException;
import Kuitso.demo.domain.Category;
import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.User;
import Kuitso.demo.dto.auth.PostSignUpRequest;
import Kuitso.demo.dto.category.GetCategoryRequest;
import Kuitso.demo.dto.category.GetCategoryResponse;
import Kuitso.demo.repository.CategoryRepository;
import Kuitso.demo.repository.ProductRepository;
import Kuitso.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Kuitso.demo.common.response.status.BaseExceptionResponseStatus.*;
import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public GetCategoryResponse info(long categoryId, GetCategoryRequest getCategoryRequest) {

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new UserException(CANNOT_FOUND_CATEGORY));

        //해당하는 카테고리 상품 찾기
        Optional<List<Product>> products = productRepository.findByCategoryList_CategoryIdAndStatusAndTradeType(categoryId, ACTIVE,getCategoryRequest.getTradeType());

        List<GetCategoryResponse.ProductDetail> productResponses = new ArrayList<>();

        if (products.isPresent()) {
            for (Product product : products.get()) {

                int wishcnt = product.getWishUserList() != null ? product.getWishUserList().size() : 0;

                GetCategoryResponse.ProductDetail productDetail = new GetCategoryResponse.ProductDetail(
                        product.getProductId(),
                        product.getPicture(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getUpdatedAt(),
                        wishcnt
                );
                productResponses.add(productDetail);
            }
        }

        // GetCategoryResponse 생성
        return new GetCategoryResponse(productResponses);


    }

}
