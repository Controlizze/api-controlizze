package com.finance.domain.category;

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
@Table(name = "categories")
public class Category {

  @Id
  @Column(name = "category_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "category")
  private List<Movement> category_movement = new ArrayList<>();

}
