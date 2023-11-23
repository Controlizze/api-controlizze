package com.finance.controller;

import com.finance.domain.user.AuthenticateRequest;
import com.finance.domain.user.User;
import com.finance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/forgot-password")
public class ResetPasswordController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> processResetPassword(@RequestBody String email) {
    return ResponseEntity.ok(userService.processResetPassword(email));
  }

  @PostMapping("/confirm")
  public ResponseEntity<String> resetPassword(@RequestBody AuthenticateRequest req) {
    userService.resetPassword(req.getEmail(), req.getPassword());

    return ResponseEntity.ok("Senha redefinida com sucesso!");
  }

}
