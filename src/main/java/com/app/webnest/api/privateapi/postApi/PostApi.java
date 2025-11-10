package com.app.webnest.api.privateapi.postApi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.PostResponseDTO;
import com.app.webnest.domain.vo.PostVO;
import com.app.webnest.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/*")
public class PostApi {

    private final PostService postService;

//    @PostMapping("write")
//    public ResponseEntity<ApiResponseDTO> writePost(@RequestBody PostVO postVO) {
//        Map<String, Long> response = postService.write(postVO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.of("게시글 작성 완료", response));
//    }

    // 열린둥지 전체 조회
    @GetMapping("/open")
    public List<PostResponseDTO> getOpenPosts() {
        return postService.getOpenPosts();
    }

    // 문제둥지 전체 조회
    @GetMapping("/question")
    public List<PostResponseDTO> getQuestionPosts() {
        return postService.getQuestionPosts();
    }

    // 상세 조회
    @GetMapping("get-post/{id}")
    public ResponseEntity<ApiResponseDTO> getPost(@PathVariable Long id) {
        PostResponseDTO post = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("게시글 조회 성공", post));
    }

    // 마이페이지 - 열린둥지 전체
    @GetMapping("/users/{userId}/open")
    public List<PostResponseDTO> getMyOpenPosts(@PathVariable Long userId){
        return postService.getOpenPostsByUserId(userId);
    }

    // 마이페이지 - 문제둥지 전체
    @GetMapping("/users/{userId}/question")
    public List<PostResponseDTO> getMyQuestionPosts(@PathVariable Long userId){
        return postService.getQuestionPostsByUserId(userId);
    }

//    @PutMapping("modify")
//    public ResponseEntity<ApiResponseDTO> updatePost(@RequestBody PostVO postVO) {
//        postService.modify(postVO);
//        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("게시글 수정 조회 성공"));
//    }
//
//    @DeleteMapping("remove")
//    public ResponseEntity<ApiResponseDTO> updatePost(@RequestBody Long id) {
//        postService.remove(id);
//        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("게시글 삭제 성공"));
//    }
}
