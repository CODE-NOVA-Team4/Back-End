package Kuitso.demo.domain;

import Kuitso.demo.domain.base.BaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Where(clause = "status = 'ACTIVE'") // ACTIVE 상태인 데이터만 조회
@EntityListeners(AuditingEntityListener.class)
public class RecentSearch {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "recent_search_id", nullable = false)
    private long recentSearchId;

    @Column(nullable = false)
    private String recentSearch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BaseStatus status = ACTIVE; // 기본값 ACTIVE

    /** 최근검색어와의 연관관계 주인 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
