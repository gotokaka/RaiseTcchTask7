package com.example.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NameController {

  @GetMapping("/names")
  public List<String> getNames() {
    return List.of("goto", "nakayama", "kinnikun");
  }

}
