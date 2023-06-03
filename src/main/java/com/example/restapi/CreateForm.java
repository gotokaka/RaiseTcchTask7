package com.example.restapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateForm {
  @NotBlank(message = "名前を入力してください")
  private String userName;
  @NotBlank(message = "IDを入力してください")
  @Pattern(regexp = "^[0-9]{3}$", message = "3桁の数字を入力してください")
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setName(String userName) {
    this.userName = userName;
  }
}
