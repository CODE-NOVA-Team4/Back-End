package Kuitso.demo.repository;

import Kuitso.demo.domain.User;
import Kuitso.demo.domain.base.BaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserIdAndStatus(Long memberId, BaseStatus active);
}
