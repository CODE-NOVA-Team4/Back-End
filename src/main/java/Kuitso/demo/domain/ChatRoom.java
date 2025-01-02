package Kuitso.demo.domain;

import Kuitso.demo.domain.base.BaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Where(clause = "status = 'ACTIVE'") // ACTIVE 상태인 데이터만 조회
@EntityListeners(AuditingEntityListener.class)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "chat_room_id", nullable = false)
    private long chatRoomId;

    @Column(name = "chat_name", nullable = false)
    private String chatName;

    @CreatedDate
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(STRING)
    @Column(nullable = false)
    private BaseStatus status = ACTIVE; // 기본값 ACTIVE

    /** 채팅방과의 연관관계 주인 */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatJoin> chatJoinList;
}
