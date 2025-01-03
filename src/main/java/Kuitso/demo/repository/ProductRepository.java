package Kuitso.demo.repository;

import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.product.GetProductSearchInfoResponse;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByCategoryList_CategoryIdAndStatusAndTradeType(Long categoryId, BaseStatus status, String tradeType);
    Optional<List<Product>> findByProductNameContaining(String keyword);

    Product findProductByProductId(Long productId);



}
