package com.finance.repo;

import com.finance.domain.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {

  Optional<Authority> findByAuthority(String name);

}
