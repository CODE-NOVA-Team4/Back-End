package Kuitso.demo.dto.recentSearch;

import Kuitso.demo.domain.RecentSearch;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetRecentSearchResponse {

    /**
     * 최근검색어조회 request dto
     */
    private List<RecentSearchDTO> recentSearches;

}
