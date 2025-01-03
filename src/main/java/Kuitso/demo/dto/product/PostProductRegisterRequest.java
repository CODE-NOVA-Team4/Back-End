package Kuitso.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostProductRegisterRequest {

    /**
     * 상품등록 request dto
     */
    private List<String> productPictures;
    private List<Long> categoryIds;
    private String productName;
    private int price;
    private String tradeType;

}
