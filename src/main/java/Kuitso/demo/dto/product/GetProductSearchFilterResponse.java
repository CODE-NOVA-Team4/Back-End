package Kuitso.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetProductSearchFilterResponse {

    /**
     * 상품조건부조회 response dto
     */
    private List<ProductDetail> products;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetail {
        private long productId;      // 상품 ID
        private String picture;        // 상품 이미지
        private String productName;    // 상품명
        private int price;             // 가격
        private LocalDateTime updatedAt;
        private int wishcnt;
    }
}
