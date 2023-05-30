package com.example.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class NameController {

  @GetMapping("/names")
  public List<String> getNames() {
    return List.of("goto", "nakayama", "kinnikun");
  }

  @PostMapping("/names")
  public ResponseEntity<String> create(@RequestBody CreateForm form) {
    // 登録処理は省略
    URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
        .path("/names/id") // id部分は実際に登録された際に発⾏したidを設定する
        .build()
        .toUri();
    return ResponseEntity.created(url).body("name successfully created");
  }

  @PatchMapping("/names/{id}")
  public ResponseEntity<Map<String, String>> updateName(@PathVariable("id") String id, @RequestBody NameUpdateForm form) {
    // パスパラメータのidを文字列で受け取る。
    String message = "名前が正常に更新されました。ID: " + id;
    return ResponseEntity.ok(Map.of("message", message));
    //return ResponseEntity.ok(Map.of("message", "name successfully updated"));
  }

}
