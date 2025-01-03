package Kuitso.demo.service;

import Kuitso.demo.domain.RecentSearch;
import Kuitso.demo.domain.base.BaseStatus;
import Kuitso.demo.repository.RecentSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RecentSearchService {

    private final RecentSearchRepository recentSearchRepository;

    public List<RecentSearch> getRecentSearchesByUserId(Long userId) {
        return recentSearchRepository.findByUser_UserIdAndStatus(userId, BaseStatus.ACTIVE);
    }

}
