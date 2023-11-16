package com.finance.controller;

import com.finance.domain.state.State;
import com.finance.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/state")
public class StateController {

  private final StateService stateService;

  @GetMapping("/all")
  public ResponseEntity<List<State>> states() {
    List<State> states = stateService.getStates();

    return ResponseEntity.ok(states);
  }

}
