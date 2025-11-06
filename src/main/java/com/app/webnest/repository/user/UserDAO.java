package com.app.webnest.repository.user;

import com.app.webnest.domain.vo.UserInsertSocialVO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
  private final UserMapper userMapper;

  // 회원 조회
  public Optional<UserVO> findById(Long id){
    return userMapper.select(id);
  }

  // 전체 조회
  public List<UserVO> findAll(){
    return userMapper.selectAll();
  }

  // 이메일로 아이디 찾기
  public Long findIdByUserEmail(String userEmail){
    return userMapper.selectIdByUserEmail(userEmail);
  }

  // 이메일 중복 확인
  public boolean existsByUserEmail(String userEmail){
    return userMapper.existsByUserEmail(userEmail);
  }

  // 회원 가입
  public void save(UserVO userVO){
    userMapper.insert(userVO);
  }

  // 회원 가입(소셜용)
  public void saveSocialUser(UserInsertSocialVO userInsertSocialVO){
    userMapper.insertSocial(userInsertSocialVO);
  }

  // 회원 수정
  public void update(UserVO userVO){
    userMapper.update(userVO);
  }

  // 회원 탈퇴
  public void delete(Long id){
    userMapper.delete(id);
  }

}
