package com.finance.repo.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MovementFilter {

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date_init;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date_end;

  private String category;

  private String type;

  private String user;

}
