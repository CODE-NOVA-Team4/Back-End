package Kuitso.demo.dto;

import Kuitso.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetHomeResponse {

    /**
     * 카테고리별 조회 response dto
     */
    private String nickname;
    private List<Product> products;

}
