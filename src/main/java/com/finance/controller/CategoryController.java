package com.finance.controller;

import com.finance.domain.category.Category;
import com.finance.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/all")
  public ResponseEntity<List<Category>> getCategories() {
    List<Category> categories = categoryService.getCategories();

    return ResponseEntity.ok(categories);
  }

}
