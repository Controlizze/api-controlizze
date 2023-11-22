package com.finance.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {

  private String name;

  private String email;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date_birth;

  private String cel;

  private Long city;

  private Long state;

}
