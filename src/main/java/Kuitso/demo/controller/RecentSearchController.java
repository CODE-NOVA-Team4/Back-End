package Kuitso.demo.controller;

import Kuitso.demo.common.response.BaseResponse;
import Kuitso.demo.domain.RecentSearch;
import Kuitso.demo.dto.recentSearch.GetRecentSearchResponse;
import Kuitso.demo.service.RecentSearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recent-search")
public class RecentSearchController {

    private final RecentSearchService recentSearchService;

    @GetMapping("/list")
    public BaseResponse<GetRecentSearchResponse> list(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Long userId = (Long)httpSession.getAttribute("userId");
        return new BaseResponse<>(recentSearchService.getRecentSearchesByUserId(userId));
    }

    @PatchMapping("/{recentSearchId}/delete")
    public BaseResponse<Void> deleteRecentSearch(@PathVariable("recentSearchId") Long recentSearchId) {
        recentSearchService.deleteRecentSearch(recentSearchId);
        return new BaseResponse<>(null);
    }

    @PatchMapping("/delete")
    public BaseResponse<Void> deleteAllRecentSearch(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Long userId = (Long)httpSession.getAttribute("userId");
        recentSearchService.deleteAllRecentSearches(userId);
        return new BaseResponse<>(null);
    }
}
