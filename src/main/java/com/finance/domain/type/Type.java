package com.finance.domain.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finance.domain.movement.Movement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "types")
public class Type {

  @Id
  @Column(name = "type_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "type")
  private List<Movement> type_movement = new ArrayList<>();

}
