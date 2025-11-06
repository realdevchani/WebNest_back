package com.app.webnest.domain.dto;

import com.app.webnest.domain.vo.UserVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String userEmail;
  private String userThumbnailUrl;
  private String userThumbnailName;
  private String userName;
  private String userNickname;
  private String userProvider;

  {
    this.setUserThumbnailUrl("/default");
    this.setUserThumbnailName("user.jpg");
    this.setUserNickname("개복치 1단계");
    this.setUserProvider("local");
  }

  public UserResponseDTO(UserVO userVO) {
    this.id =  userVO.getId();
    this.userEmail = userVO.getUserEmail();
    this.userThumbnailUrl = userVO.getUserThumbnailUrl();
    this.userThumbnailName = userVO.getUserThumbnailName();
    this.userName = userVO.getUserName();
    this.userNickname = userVO.getUserNickname();
    this.userProvider = userVO.getUserProvider();
  }
}
