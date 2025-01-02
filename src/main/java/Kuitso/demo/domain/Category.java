package Kuitso.demo.domain;

import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.domain.base.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.List;

import static Kuitso.demo.domain.base.BaseStatus.ACTIVE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Where(clause = "status = 'ACTIVE'") // ACTIVE 상태인 데이터만 조회
public class Category extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id", nullable = false)
    private long categoryId;

    @Column(nullable = false)
    private String categoryName;

    @Enumerated(STRING)
    @Column(nullable = false)
    private BaseStatus status = ACTIVE; // 기본값 ACTIVE

    @ManyToMany(mappedBy = "categoryList")
    private List<Product> productList;


}
