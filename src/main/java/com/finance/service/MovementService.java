package com.finance.service;

import com.finance.domain.category.Category;
import com.finance.domain.movement.Movement;
import com.finance.domain.movement.MovementRequest;
import com.finance.domain.type.Type;
import com.finance.domain.user.User;
import com.finance.repo.CategoryRepo;
import com.finance.repo.MovementRepo;
import com.finance.repo.TypeRepo;
import com.finance.repo.UserRepo;
import com.finance.repo.filter.MovementFilter;
import com.finance.repo.movement.MovFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovementService {

  private final MovementRepo movementRepo;
  private final CategoryRepo categoryRepo;
  private final TypeRepo typeRepo;
  private final UserRepo userRepo;

  public Page<MovFilterDTO> search(MovementFilter movementFilter, Pageable pageable) {
    return movementRepo.filter(movementFilter, pageable);
  }

  public List<Movement> getMovements() {
    return movementRepo.findAll();
  }

  public void addMovement(MovementRequest req) {
    Optional<Category> optionalCategory = categoryRepo.findById(req.getCategory());
    Optional<Type> optionalType = typeRepo.findById(req.getType());
    Optional<User> optionalUser = userRepo.findById(req.getUser());
    Category category = optionalCategory.get();
    Type type = optionalType.get();
    User user = optionalUser.get();
    LocalDate date = LocalDate.parse(req.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    var newMovement = Movement.builder()
            .id(null)
            .date(date)
            .description(req.getDescription())
            .category(category)
            .value(req.getValue())
            .type(type)
            .user(user)
            .build();

    movementRepo.save(newMovement);
  }

  public void updateMovement(Long id, MovementRequest req) {
    Optional<Movement> optionalMovement = movementRepo.findById(id);
    Optional<Category> optionalCategory = categoryRepo.findById(req.getCategory());
    Optional<Type> optionalType = typeRepo.findById(req.getType());
    Optional<User> optionalUser = userRepo.findById(req.getUser());
    LocalDate date = LocalDate.parse(req.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    if (optionalMovement.isEmpty()) {
      throw new RuntimeException("Registro não encontrado");
    }

    Movement movement = optionalMovement.get();
    Category category = optionalCategory.get();
    Type type = optionalType.get();
    User user = optionalUser.get();

    movement.setDate(date);
    movement.setDescription(req.getDescription());
    movement.setCategory(category);
    movement.setValue(req.getValue());
    movement.setType(type);
    movement.setUser(user);

    movementRepo.save(movement);
  }

  public void deleteMovement(Long id) {
    Optional<Movement> optionalMovement = movementRepo.findById(id);

    if (optionalMovement.isEmpty()) {
      throw new RuntimeException("Registro não encontrado");
    }

    Movement movement = optionalMovement.get();

    movementRepo.delete(movement);
  }

}
