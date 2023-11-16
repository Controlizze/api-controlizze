package com.finance.controller;

import com.finance.domain.city.City;
import com.finance.domain.city.CityRequest;
import com.finance.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

  private final CityService cityService;

  @GetMapping("/all")
  public ResponseEntity<List<City>> cities() {
    List<City> cities = cityService.getCities();

    return ResponseEntity.ok(cities);
  }

  @PostMapping("/add")
  public ResponseEntity<String> addCity(@RequestBody CityRequest req) {
    cityService.addCity(req);

    return ResponseEntity.ok("Cidade adicionado com sucesso!");
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody CityRequest req) {
    cityService.updateCity(id, req);

    return ResponseEntity.ok("Cidade alterada com sucesso!");
  }

}
