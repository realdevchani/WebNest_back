package com.app.webnest.mapper;

import com.app.webnest.domain.vo.UserSocialVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSocialMapper {

  // 소셜 테이블에 추가
  public void insert(UserSocialVO userSocialVO);

  // 회원의 Provider를 전체조회
  public List<String> selectAll(Long id);

}
