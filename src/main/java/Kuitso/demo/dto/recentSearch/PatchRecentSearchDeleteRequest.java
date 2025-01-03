package Kuitso.demo.dto.recentSearch;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PatchRecentSearchDeleteRequest {

    /**
     * 최근검색어삭제 request dto
     */
    private long recentSearchId;
    
}
