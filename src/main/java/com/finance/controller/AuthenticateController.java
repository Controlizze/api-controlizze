package com.finance.controller;

import com.finance.domain.user.AuthenticateRequest;
import com.finance.domain.user.AuthenticateResponse;
import com.finance.domain.user.RegisterRequest;
import com.finance.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticateController {

  private final AuthenticateService authenticateService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
    authenticateService.register(req);

    return ResponseEntity.ok("Usuário registrado com sucesso!");
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticateResponse> login(@RequestBody AuthenticateRequest req) {
    return ResponseEntity.ok(authenticateService.login(req));
  }

}
