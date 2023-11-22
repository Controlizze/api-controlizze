package com.finance.domain.user;

import lombok.Data;

@Data
public class AuthenticateRequest {

  private String email;

  private String password;

}
