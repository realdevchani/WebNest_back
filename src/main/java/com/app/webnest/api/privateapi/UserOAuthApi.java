package com.app.webnest.api.privateapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/users/*")
@RequiredArgsConstructor
@Slf4j
public class UserOAuthApi {

    @GetMapping("me")
    public ResponseEntity<ApiResponseDTO> me(Authentication authentication){
        UserResponseDTO currentUser = (UserResponseDTO)authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("최초 로그인 성공", currentUser));
    }

}
