package com.app.webnest.domain.vo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertSocialVO {
  private Long id;
  private String userEmail;
  private String userThumbnailUrl;
  private String userThumbnailName;
  private String userName;
  private String userNickname;
  private String userProvider;


  {
    this.setUserThumbnailName("default.jpg");
    this.setUserThumbnailUrl("/default");
    this.setUserNickname(this.getUserName());
    this.setUserProvider("local");
  }

}
