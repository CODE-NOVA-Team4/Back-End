package Kuitso.demo.service;

import Kuitso.demo.domain.Category;
import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.ProductImage;
import Kuitso.demo.domain.User;
import Kuitso.demo.dto.product.GetProductSearchInfoResponse;
import Kuitso.demo.repository.ProductRepository;
import Kuitso.demo.repository.UserRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static Kuitso.demo.dto.product.GetProductSearchInfoResponse.*;
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
    private final UserRepository userRepository;

    @Transactional
    public void addWishProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();

        if (!user.getWishProductList().contains(product)) {
            user.getWishProductList().add(product);
        } else {
            user.getWishProductList().remove(product);
        }
    }

    public GetProductSearchInfoResponse getProductInfoById(Long productId, Long userId) {
        Product product = productRepository.findProductByProductId(productId);
        List<PictureDto> productImages = product.getProductImageListList().stream()
                .map(productImage -> new PictureDto(productImage.getProductImage()))  // PictureDto로 변환
                .toList();

        // categoryList를 CategoryDto 리스트로 변환
        List<CategoryDto> categories = product.getCategoryList().stream()
                .map(category -> new CategoryDto(category.getCategoryName()))  // CategoryDto로 변환
                .toList();
        String productName = product.getProductName();
        String description = product.getDescription();
        int price = product.getPrice();
        String isOwner;

        User sellUser = product.getSellUser();
        if (sellUser.equals(userRepository.findById(userId).get())) {
            isOwner = "owner";
        } else{
            isOwner = "basic";
        }

        return new GetProductSearchInfoResponse(productImages, categories, productName, description, price, isOwner);
    }

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
