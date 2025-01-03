package Kuitso.demo.repository;

import Kuitso.demo.domain.Category;
import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<User> findByCategoryIdAndStatus(Long CategoryId, BaseStatus active);
}
