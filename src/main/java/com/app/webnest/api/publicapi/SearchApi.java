package com.app.webnest.api.publicapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.PostSearchDTO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        List<UserVO> users = searchService.getUserBySearchQuery(query);
        List<PostSearchDTO> questionPosts = searchService.getQuestionPostBySearchQuery(query);
        List<PostSearchDTO> openPosts = searchService.getOpenPostBySearchQuery(query);
        List<QuizVO> quizzes = searchService.getQuizBySearchQuery(query);
        result.put("search", query);

        int total = 0;

        result.put("openPosts", openPosts);
        total += openPosts.size();

        result.put("questionPosts", questionPosts);
        total += questionPosts.size();

        result.put("quizzes", quizzes);
        total += quizzes.size();

        result.put("users", users);
        total += users.size();

        result.put("total", total);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("정보 수정이 완료되었습니다.", result));
    }
}
