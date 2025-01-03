package Kuitso.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductSearchRequest {

    /**
     * 상품검색 request dto
     */
    private String recentSearch;
}
