package Kuitso.demo.dto.user;

import Kuitso.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserProductResponse {

    /**
     * 구매내역조회,판매내역조회,관심상품조회
     * response dto
     */
    private List<Product> products;
}
