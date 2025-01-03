package Kuitso.demo.service;

import Kuitso.demo.domain.RecentSearch;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.dto.recentSearch.GetRecentSearchResponse;
import Kuitso.demo.dto.recentSearch.RecentSearchDTO;
import Kuitso.demo.repository.RecentSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentSearchService {

    private final RecentSearchRepository recentSearchRepository;

    public GetRecentSearchResponse getRecentSearchesByUserId(Long userId) {

        List<RecentSearchDTO> recentSearchDTOList = recentSearchRepository
                .findByUser_UserIdAndStatus(userId, BaseStatus.ACTIVE)
                .stream()
                .map(recentSearch -> new RecentSearchDTO(recentSearch.getRecentSearchId(), recentSearch.getRecentSearch()))
                .collect(Collectors.toList());

        return new GetRecentSearchResponse(recentSearchDTOList);
    }

    @Transactional
    public void deleteRecentSearch(Long recentSearchId) {
        RecentSearch findRecentSearchId = recentSearchRepository.findByRecentSearchId(recentSearchId);
        findRecentSearchId.changeStatus(BaseStatus.DELETED);
    }

    @Transactional
    public void deleteAllRecentSearches(Long userId) {
        List<RecentSearch> recentSearches = recentSearchRepository.findByUser_UserIdAndStatus(userId, BaseStatus.ACTIVE);
        for (RecentSearch search : recentSearches) {
            search.changeStatus(BaseStatus.DELETED);
        }
    }

}
