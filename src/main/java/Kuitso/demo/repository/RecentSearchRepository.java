package Kuitso.demo.repository;

import Kuitso.demo.domain.RecentSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentSearchRepository extends JpaRepository<RecentSearch, Long> {
}
