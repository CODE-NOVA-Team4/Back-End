package Kuitso.demo.domain;

import Kuitso.demo.domain.base.BaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

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
public class ProductImage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_image_id", nullable = false)
    private long productImageId;

    @Column(name = "product_image", nullable = false)
    private String productImage;

    @Enumerated(STRING)
    @Column(nullable = false)
    private BaseStatus status = ACTIVE; // 기본값 ACTIVE

    /** 상품이미지의 연관관계 주인 **/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public ProductImage(String picture, Product product, BaseStatus baseStatus) {
        this.productImage = picture;
        this.product = product;
        this.status = baseStatus;
    }
}
