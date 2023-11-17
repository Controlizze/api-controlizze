package com.finance.domain.movement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finance.domain.category.Category;
import com.finance.domain.type.Type;
import com.finance.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movements")
public class Movement {

  @Id
  @Column(name = "mov_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date;

  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  private Long value;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private Type type;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
