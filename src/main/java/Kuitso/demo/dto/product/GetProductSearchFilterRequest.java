package Kuitso.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductSearchFilterRequest {

    /**
     * 상품조건부조회 request dto
     */
    private String keyword;
    private String tradeType;
}
