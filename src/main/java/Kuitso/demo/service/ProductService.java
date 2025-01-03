package Kuitso.demo.service;

import Kuitso.demo.common.exception.ProductException;
import Kuitso.demo.common.exception.UserException;
import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.category.GetCategoryResponse;
import Kuitso.demo.dto.product.GetProductSearchFilterRequest;
import Kuitso.demo.dto.product.GetProductSearchRequest;
import Kuitso.demo.dto.product.GetProductSearchResponse;
import Kuitso.demo.repository.CategoryRepository;
import Kuitso.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Kuitso.demo.common.response.status.BaseExceptionResponseStatus.*;
import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public GetProductSearchResponse search(GetProductSearchRequest getProductSearchRequest) {
        String recentSearch = getProductSearchRequest.getRecentSearch();

        Optional<List<Product>> productList = productRepository.findByProductNameContaining(recentSearch);
        if (!productList.isPresent()) {
            throw new ProductException( NOT_EXIST_PRODUCTLIST);
        }

        List<GetProductSearchResponse.ProductDetail> productResponses = new ArrayList<>();

            for (Product product : productList.get()) {

                int wishcnt = product.getWishUserList() != null ? product.getWishUserList().size() : 0;

                GetProductSearchResponse.ProductDetail productDetail = new GetProductSearchResponse.ProductDetail(
                        product.getProductId(),
                        product.getPicture(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getUpdatedAt(),
                        wishcnt
                );
                productResponses.add(productDetail);
            }

        return new GetProductSearchResponse(recentSearch,productResponses);
    }

//    public GetProductSearchFilterRequest searchFilter( long categoryId,GetProductSearchFilterRequest getProductSearchFilterRequest) {
//
//        categoryRepository.findByCategoryIdAndStatus(categoryId,ACTIVE)
//                .orElseThrow(() -> new ProductException(CANNOT_FOUND_PRODUCT));
//
//    }
}
