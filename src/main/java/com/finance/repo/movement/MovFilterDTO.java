package com.finance.repo.movement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovFilterDTO {

  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date;

  private String description;

  private String category;

  private Long value;

  private String type;

  private String user;

}
