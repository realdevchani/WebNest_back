package com.app.webnest.repository.user;

import com.app.webnest.domain.vo.UserSocialVO;
import com.app.webnest.mapper.UserSocialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSocialDAO {

  final UserSocialMapper userSocialMapper;

  // 소셜 정보 추가
  public void save(UserSocialVO userSocialVO){
    userSocialMapper.insert(userSocialVO);
  }

  // 소셜 프로바이더 조회
  public List<String> findSocialProvidersById(Long id){
    return userSocialMapper.selectAll(id);
  }

}
