package Kuitso.demo.dto.product;

import Kuitso.demo.domain.Category;
import Kuitso.demo.domain.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetProductSearchInfoResponse {
    /**
     * 상품상세정보조회 request dto
     */
    private List<PictureDto> productImageListList;
    private List<CategoryDto> categoryList;
    private String productName;
    private String description;
    private int price;
    private String isOwner;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PictureDto {
        private String picture;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDto {
        private String category;
    }


}
