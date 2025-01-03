package Kuitso.demo.service;

import Kuitso.demo.common.exception.ProductException;
import Kuitso.demo.common.exception.UserException;
import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.User;
import Kuitso.demo.dto.HomeResponse;
import Kuitso.demo.dto.category.GetCategoryRequest;
import Kuitso.demo.dto.category.GetCategoryResponse;
import Kuitso.demo.repository.ProductRepository;
import Kuitso.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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
@Transactional
public class HomeService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public HomeResponse getHomeData(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new UserException(CANNOT_FOUND_SESSION);
        }

        Long userId = (Long) session.getAttribute("userId");
        User user = userRepository.findByUserIdAndStatus(userId, ACTIVE)
                .orElseThrow(() -> new UserException(CANNOT_FOUND_USER));
        String nickname = user.getNickName();

        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            throw new ProductException(CANNOT_FOUND_PRODUCTLIST);
        }

        List<HomeResponse.ProductDetail> productDetailList = new ArrayList<>();
        for (Product product : productList) {
            HomeResponse.ProductDetail productDetail = new HomeResponse.ProductDetail(
                    product.getProductId(),
                    product.getPicture(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getUpdatedAt()
            );
            productDetailList.add(productDetail);
        }
        return new HomeResponse(nickname, productDetailList);
    }
}

