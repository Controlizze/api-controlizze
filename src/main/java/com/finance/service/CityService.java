package com.finance.service;

import com.finance.domain.city.City;
import com.finance.repo.CityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

  private final CityRepo cityRepo;

  public List<City> getCities() {
    return cityRepo.findAll();
  }

}
