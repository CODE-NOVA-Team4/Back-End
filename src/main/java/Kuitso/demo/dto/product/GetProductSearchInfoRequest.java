package Kuitso.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductSearchInfoRequest {

    /**
     * 상품상세정보조회 request dto
     */
    private long productId;
}
