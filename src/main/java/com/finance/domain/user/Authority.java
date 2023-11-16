package com.finance.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

  @Id
  @Column(name = "authority_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String authority;

  @Override
  public String getAuthority() {
    return authority;
  }

}
