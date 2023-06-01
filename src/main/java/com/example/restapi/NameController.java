package com.example.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class NameController {

  @GetMapping("/users")
  public String getUser(
      @RequestParam(name = "name", value = "name", defaultValue = "なし", required = false) String name,
      @RequestParam(name = "id", defaultValue = "なし", required = false) String id,
      //誕生日を取得するパラメータの追加
      @RequestParam(name = "birthDate") String birthDate) {
    //returnで返す値を設定。
    return "ID：" + id + "名前：" + name + "生年月日：" + birthDate;
  }

  //GETリクエストをListで返すメソッド
  @GetMapping("/names")
  public List<String> getNames() {
    return List.of("goto", "nakayama", "kinnikun");
  }

  //POSTリクエストを返すメソッド
  @PostMapping("/names")
  public ResponseEntity<String> create(
      @RequestBody CreateForm form) {
    // 登録処理は省略
    URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
        .path("/names/id") // id部分は実際に登録された際に発⾏したidを設定する
        .build()
        .toUri();
    return ResponseEntity.created(url).body("name successfully created");
  }

  //PATCHリクエストを返すメソッド
  @PatchMapping("/names/{id}")
  public ResponseEntity<Map<String, String>> update(
      @PathVariable("id") String id,
      @RequestBody NameUpdateForm form) {
    return ResponseEntity.ok(Map.of("message", "name successfully updated"));
  }
}


