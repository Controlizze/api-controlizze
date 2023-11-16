package com.finance.domain.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finance.domain.city.City;
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
@Table(name = "states")
public class State {

  @Id
  @Column(name = "state_id")
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String uf;

  @JsonIgnore
  @OneToMany(mappedBy = "state")
  private List<City> city_state = new ArrayList<>();

}
