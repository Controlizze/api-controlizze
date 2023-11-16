package com.finance.repo.movement;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovFilterDTO {

  private Long id;
  private LocalDate date;
  private String description;
  private String category;
  private String value;
  private String type;
  private String user;

}
