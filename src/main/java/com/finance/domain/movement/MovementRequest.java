package com.finance.domain.movement;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MovementRequest {

  private String date;
  private String description;
  private Long category;
  private Long value;
  private Long type;
  private String user_email;

}
