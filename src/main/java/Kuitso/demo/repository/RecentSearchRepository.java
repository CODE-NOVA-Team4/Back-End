package Kuitso.demo.repository;

import Kuitso.demo.domain.RecentSearch;
import Kuitso.demo.domain.base.BaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecentSearchRepository extends JpaRepository<RecentSearch, Long> {

    List<RecentSearch> findByUser_UserIdAndStatus(Long userId, BaseStatus status);
}
