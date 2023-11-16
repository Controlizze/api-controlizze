package com.finance.service;

import com.finance.domain.state.State;
import com.finance.repo.StateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {

  private final StateRepo stateRepo;

  public List<State> getStates() {
    return stateRepo.findAll();
  }

}
