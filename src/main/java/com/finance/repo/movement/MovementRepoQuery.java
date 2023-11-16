package com.finance.repo.movement;

import com.finance.repo.filter.MovementFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepoQuery {

  Page<MovFilterDTO> filter(MovementFilter movementFilter, Pageable pageable);

}
