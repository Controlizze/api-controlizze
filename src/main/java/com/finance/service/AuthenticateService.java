package com.finance.service;

import com.finance.domain.user.*;
import com.finance.repo.AuthorityRepo;
import com.finance.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;
  private final UserRepo userRepo;
  private final AuthorityRepo authorityRepo;

  public void register(RegisterRequest req) {
    Authority client = authorityRepo.findByAuthority("CLIENT").get();
    Set<Authority> authorities = new HashSet<>();
    authorities.add(client);

    var user = User.builder()
            .name(req.getName())
            .email(req.getEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .authorities(authorities)
            .build();

    userRepo.save(user);
  }

  public AuthenticateResponse login(AuthenticateRequest req) {
    Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
    );

    var user  = userRepo.findByEmail(req.getEmail()).orElseThrow();
    var token = tokenService.generateJwt(auth);

    return AuthenticateResponse.builder()
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .token(token)
            .build();
  }

}
