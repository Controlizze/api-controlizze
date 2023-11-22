package com.finance.controller;

import com.finance.domain.movement.Movement;
import com.finance.domain.movement.MovementRequest;
import com.finance.repo.filter.MovementFilter;
import com.finance.repo.movement.MovFilterDTO;
import com.finance.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movement")
public class MovementController {

  private final MovementService movementService;

  @GetMapping
  public ResponseEntity<Page<MovFilterDTO>> filter(MovementFilter movementFilter, Pageable pageable) {
    Page<MovFilterDTO> movements = movementService.search(movementFilter, pageable);

    return ResponseEntity.ok(movements);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Movement>> movements() {
    List<Movement> movements = movementService.getMovements();

    return ResponseEntity.ok(movements);
  }

  @PostMapping("/add")
  public ResponseEntity<String> addMovement(@RequestBody MovementRequest req) {
    movementService.addMovement(req);

    return ResponseEntity.ok("Registro adicionado com sucesso!");
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody MovementRequest req) {
    movementService.updateMovement(id, req);

    return ResponseEntity.ok("Registro alterado com sucesso!");
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteMovement(@PathVariable Long id) {
    movementService.deleteMovement(id);

    return ResponseEntity.ok("Registro deletado com sucesso!");
  }

}
