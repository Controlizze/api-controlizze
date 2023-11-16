package com.finance.repo;

import com.finance.domain.movement.Movement;
import com.finance.repo.movement.MovementRepoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepo extends JpaRepository<Movement, Long>, MovementRepoQuery {
}
