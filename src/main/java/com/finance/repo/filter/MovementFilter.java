package com.finance.repo.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MovementFilter {

  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private LocalDate date_init;

  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private LocalDate date_end;
  private String category;
  private String type;
  private String user;

}
