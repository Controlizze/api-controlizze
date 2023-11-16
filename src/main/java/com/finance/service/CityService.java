package com.finance.service;

import com.finance.domain.city.City;
import com.finance.domain.city.CityRequest;
import com.finance.domain.state.State;
import com.finance.repo.CityRepo;
import com.finance.repo.StateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

  private final CityRepo cityRepo;
  private final StateRepo stateRepo;

  public List<City> getCities() {
    return cityRepo.findAll();
  }

  public void addCity(CityRequest req) {
    Optional<State> optionalState = stateRepo.findById(req.getState());
    State state = optionalState.get();

    var newCity = City.builder()
            .id(null)
            .name(req.getName())
            .state(state)
            .build();

    cityRepo.save(newCity);
  }

  public void updateCity(Long id, CityRequest req) {
    Optional<City> optionalCity = cityRepo.findById(id);
    Optional<State> optionalState = stateRepo.findById(req.getState());

    if (optionalCity.isEmpty()) {
      throw  new RuntimeException("Cidade não encontrada");
    }

    City city = optionalCity.get();
    State state = optionalState.get();

    city.setName(req.getName());
    city.setState(state);

    cityRepo.save(city);
  }

  public void deleteCity(Long id) {
    Optional<City> optionalCity = cityRepo.findById(id);

    if (optionalCity.isEmpty()) {
      throw  new RuntimeException("Cidade não encontrada");
    }

    City city = optionalCity.get();

    cityRepo.delete(city);
  }

}
