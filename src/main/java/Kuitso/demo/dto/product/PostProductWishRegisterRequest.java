package Kuitso.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostProductWishRegisterRequest {

    /**
     * 관심상품등록 request dto
     */
    private long productId;

}
