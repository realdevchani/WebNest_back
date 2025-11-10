package com.app.webnest.api.publicapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")  // 추가
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchApi {
    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> searchResult(@RequestParam("search") String query) {
        Map<String, Object> result = new HashMap<>();
        result.put("search", query);

        int total = 0;

        try {
            var openPosts = searchService.getOpenPostBySearchQuery(query);
            if (!openPosts.isEmpty()) {
                result.put("openPosts", openPosts);
                total += openPosts.size();
            }
        } catch (Exception e) {
            result.put("openPosts_error", e.getClass().getSimpleName() + ": " + String.valueOf(e.getMessage()));
        }

        try {
            var questionPosts = searchService.getQuestionPostBySearchQuery(query);
            if (!questionPosts.isEmpty()) {
                result.put("questionPosts", questionPosts);
                total += questionPosts.size();
            }
        } catch (Exception e) {
            result.put("questionPosts_error", e.getClass().getSimpleName() + ": " + String.valueOf(e.getMessage()));
        }

        try {
            var quizzes = searchService.getQuizBySearchQuery(query);
            if (!quizzes.isEmpty()) {
                result.put("quizzes", quizzes);
                total += quizzes.size();
            }
        } catch (Exception e) {
            result.put("quizzes_error", e.getClass().getSimpleName() + ": " + String.valueOf(e.getMessage()));
        }

        try {
            var users = searchService.getUserBySearchQuery(query);
            if (!users.isEmpty()) {
                result.put("users", users);
                total += users.size();
            }
        } catch (Exception e) {
            result.put("users_error", e.getClass().getSimpleName() + ": " + String.valueOf(e.getMessage()));
        }

        result.put("total", total);
        return ResponseEntity.ok(ApiResponseDTO.of("검색 결과 (디버그)", result));
    }
}
