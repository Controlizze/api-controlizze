package com.finance.domain.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finance.domain.state.State;
import com.finance.domain.user.User;
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
@Table(name = "cities")
public class City {

  @Id
  @Column(name = "city_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "state_id")
  private State state;

  @JsonIgnore
  @OneToMany(mappedBy = "city")
  private List<User> user_city = new ArrayList<>();

}
