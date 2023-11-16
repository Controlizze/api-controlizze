package com.finance.service;

import com.finance.domain.type.Type;
import com.finance.repo.TypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

  private final TypeRepo typeRepo;

  public List<Type> getTypes() {
    return typeRepo.findAll();
  }

}
