package com.finance.service;

import com.finance.domain.category.Category;
import com.finance.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepo categoryRepo;

  public List<Category> getCategories() {
    return categoryRepo.findAll();
  }

}
