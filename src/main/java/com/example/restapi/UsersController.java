package com.example.restapi;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

@Validated
@RestController
public class UsersController {

  //GETリクエストを返すメソッド
  @GetMapping("/users")
  public String getUser(
      @RequestParam("userName")
      @NotBlank(message = "名前を入力してください") String userName,

      @RequestParam("id")
      @NotBlank(message = "idが空白です。入力してください。")
      @Pattern(regexp = "^[0-9]{3}$", message = "半角英数で3桁の数字を入力してください") String id,

      @RequestParam("birthDate")
      @DateTimeFormat(pattern = "yyyy/MM/dd")
      LocalDate birthDate) {

    return "入力情報  " + "ユーザID：" + id + " 名前：" + userName + " 生年月日：" + birthDate;
  }

  //POSTリクエストを返すメソッド
  @PostMapping("/users")
  public ResponseEntity<Map<String, String>> create(
      @RequestBody
      @Validated
      CreateForm createForm) {
    // 登録処理は省略
    URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
        .path("/userName/id") // id部分は実際に登録された際に発⾏したidを設定する
        .build()
        .toUri();
    return ResponseEntity.ok(Map.of("message", "name successfully created"));
  }

  //PATCHリクエストを返すメソッド
  @PatchMapping("/users/{id}")
  public ResponseEntity<Map<String, String>> update(
      @PathVariable("id") String id,
      @RequestBody
      @Validated
      NameUpdateForm form) {
    //更新処理は省略
    return ResponseEntity.ok(Map.of("message", "name successfully updated"));
  }

  //DELETEリクエストを返すメソッド
  @DeleteMapping("/users/{id}")
  public ResponseEntity<Map<String, String>> delete(
      @PathVariable("id") String id) {
    return ResponseEntity.ok(Map.of("message", "name successfully deleted"));
  }
}

