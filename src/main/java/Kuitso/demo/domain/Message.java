package Kuitso.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Message {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "message_id", nullable = false)
    private long messageId;

    @Column(name = "content", nullable = false)
    private String messageContent;

    @CreatedDate
    @Column(name = "send_time",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = LAZY)
    @Column(name = "chat_join_id", nullable = false)
    private ChatJoin chatJoin;


}
