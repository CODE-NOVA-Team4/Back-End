package Kuitso.demo.dto.product;

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
    private List<PictureDto> pictures;
    private List<CategoryDto> categories;
    private String productName;
    private String description;
    private String price;
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
