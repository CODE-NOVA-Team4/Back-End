package Kuitso.demo.dto.product;

import lombok.Getter;

@Getter
public class SellBuyWishProductDTO {

    private Long productId;
    private String picture;
    private String productName;
    private int price;
    private String description;

    public SellBuyWishProductDTO(Long productId, String picture, String productName, int price, String description) {
        this.productId = productId;
        this.picture = picture;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }
}
