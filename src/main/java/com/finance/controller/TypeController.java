package com.finance.controller;

import com.finance.domain.type.Type;
import com.finance.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type")
public class TypeController {

  private final TypeService typeService;

  @GetMapping("/all")
  public ResponseEntity<List<Type>> types() {
    List<Type> types = typeService.getTypes();
    return ResponseEntity.ok(types);
  }

}
