package Kuitso.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeResponse {

    /**
     * 카테고리별 조회 response dto
     */
    private String nickname;
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
    }

}