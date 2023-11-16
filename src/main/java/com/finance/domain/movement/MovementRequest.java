package com.finance.domain.movement;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MovementRequest {

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private LocalDate date;
  private String description;
  private Long category;
  private Long value;
  private Long type;
  private Long user;

}
