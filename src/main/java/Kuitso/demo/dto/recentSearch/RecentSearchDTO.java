package Kuitso.demo.dto.recentSearch;

import lombok.Getter;

@Getter
public class RecentSearchDTO {
    private Long recentSearchId;
    private String recentSearch;

    public RecentSearchDTO(Long recentSearchId, String recentSearch) {
        this.recentSearchId = recentSearchId;
        this.recentSearch = recentSearch;
    }
}
