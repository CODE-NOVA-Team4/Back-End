package Kuitso.demo.domain;

import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.domain.base.BaseTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
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
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String picture;

    @Column(name = "sell_status",nullable = false)
    private String sellStatus;

    @Column(name = "trade_type",nullable = false)
    private String tradeType;

    @Column(nullable = false)
    private String description;

    @Enumerated(STRING)
    @Column(nullable = false)
    private BaseStatus status = ACTIVE; // 기본값 ACTIVE

    /** 상품을 등록한 사용자 /상품과의 연관관계 주인 **/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;

    @ManyToOne
    @JoinColumn(name = "sell_user_id")
    private User sellUser;

    @ManyToOne
    @JoinColumn(name = "buy_user_id")
    private User buyUser;

    @ManyToMany(mappedBy = "wishProductList")
    private List<User> wishUserList;

    @OneToMany(mappedBy ="product")
    private List<ProductImage> productImageListList;

    @OneToMany(mappedBy = "product")
    private List<ChatRoom> chatRoomList = new ArrayList<>();

    public Product(long productId,String picture, String productName, int price,String description) {
        this.productId = productId;
        this.picture = picture;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }


}
