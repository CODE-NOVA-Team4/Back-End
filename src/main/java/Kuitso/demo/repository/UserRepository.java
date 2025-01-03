package Kuitso.demo.repository;

import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.product.SellBuyWishProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndStatus(String email, BaseStatus baseStatus);
    Optional<User> findByUserIdAndStatus(Long memberId, BaseStatus active);

    @Query("""
            SELECT new Kuitso.demo.dto.product.SellBuyWishProductDTO(p.productId, p.picture, p.productName, p.price, p.description) \
            FROM User u JOIN u.buyProductList p WHERE u.userId = :userId""")
    Optional<List<SellBuyWishProductDTO>> findBuyProduct(@Param("userId") Long userId);

    @Query("""
            SELECT new Kuitso.demo.dto.product.SellBuyWishProductDTO(p.productId, p.picture, p.productName, p.price, p.description) \
            FROM User u JOIN u.sellProductList p WHERE u.userId = :userId""")
    Optional<List<SellBuyWishProductDTO>> findSellProduct(@Param("userId") Long userId);

    @Query("""
        SELECT new Kuitso.demo.dto.product.SellBuyWishProductDTO(p.productId, p.picture, p.productName, p.price, p.description)
        FROM Product p JOIN p.wishUserList u WHERE u.userId = :userId""")
    Optional<List<SellBuyWishProductDTO>> findWishProduct(@Param("userId") Long userId);

    Optional<User> findByEmailAndStatus(String email, BaseStatus baseStatus);
}
