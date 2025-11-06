package com.app.webnest.service.user;

import com.app.webnest.domain.dto.UserResponseDTO;
import com.app.webnest.domain.vo.UserInsertSocialVO;
import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.exception.UserException;
import com.app.webnest.repository.user.UserDAO;
import com.app.webnest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;
  private final UserSocialService userSocialService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;

  // 이메일 중복 조회
  @Override
  public boolean existsByUserEmail(String userEmail) {
    return userDAO.existsByUserEmail(userEmail);
  }

  // 회원가입
  @Override
  public Map<String, String> register(UserVO userVO) {

    // 1. 이메일 중복검사
    if(userDAO.existsByUserEmail(userVO.getUserEmail())) {
      throw new UserException("이미 존재하는 회원입니다");
    }

    // 2. 비밀번호 암호화
    userVO.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()));

    // 3. 회원 가입
    userDAO.save(userVO);
    return Map.of();
  }

  // 회원가입 소셜 (비밀번호가 없다)
  @Override
  public Map<String, String> registerSocial(
      UserInsertSocialVO userInsertSocialVO,
      UserSocialVO userSocialVO
  ) {

    Map<String, String> claim = new HashMap<>();
    Map<String, String> tokens = new HashMap<>();

    if(userDAO.existsByUserEmail(userInsertSocialVO.getUserEmail())) {
      throw new UserException("이미 존재하는 회원입니다.");
    }

    // 회원 가입
    userDAO.saveSocialUser(userInsertSocialVO);

    // 가입한 회원 정보
    String userEmail = userInsertSocialVO.getUserEmail();

    // 가입한 회원의 ID
    Long userId = userDAO.findIdByUserEmail(userEmail);

    String refreshToken = jwtTokenUtil.generateRefreshToken(claim);
    String accessToken = jwtTokenUtil.generateAccessToken(claim);

    // 소셜 테이블에 추가
    userSocialVO.setUserId(userId);
    userSocialService.registerUserSocial(userSocialVO);

    // 토큰을 담아서 반환
    tokens.put("accessToken", accessToken);
    tokens.put("refreshToken", refreshToken);

    return tokens;
  }

  // 회원 이메일로 아이디 조회
  @Override
  public Long getUserIdByUserEmail(String userEmail) {
    return userDAO.findIdByUserEmail(userEmail);
  }

  // 회원 조회
  @Override
  public UserResponseDTO getUserById(Long id) {
    return userDAO.findById(id).map(UserResponseDTO::new).orElseThrow(() -> new UserException("회원 조회 실패"));
  }

  // 회원 정보 수정
  @Override
  public void modify(UserVO userVO) {
    userDAO.update(userVO);
  }

  // 회원 탈퇴
  @Override
  public void withdraw(Long id) {
    userDAO.delete(id);
  }
}
