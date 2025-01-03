package Kuitso.demo.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCategoryRequest {

    /**
     * 카테고리별 조회 request dto
     */
    private String tradeType;
}
