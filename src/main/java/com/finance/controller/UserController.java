package com.finance.controller;

import com.finance.domain.user.User;
import com.finance.domain.user.UserRequest;
import com.finance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<User>> getUsers() {
    List<User> users = userService.getUsers();

    return ResponseEntity.ok(users);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest req) {
    userService.updateUser(id, req);

    return ResponseEntity.ok("Dados alterados com sucesso!");
  }

}
