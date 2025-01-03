package Kuitso.demo.repository;

import Kuitso.demo.domain.ChatJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatJoinRepository extends JpaRepository<ChatJoin, Long> {
}
