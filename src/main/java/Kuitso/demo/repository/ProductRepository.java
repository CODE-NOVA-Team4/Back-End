package Kuitso.demo.repository;

import Kuitso.demo.domain.Product;
import Kuitso.demo.domain.base.BaseStatus;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByCategoryList_CategoryIdAndStatusAndTradeType(Long categoryId, BaseStatus status, String tradeType);

}
