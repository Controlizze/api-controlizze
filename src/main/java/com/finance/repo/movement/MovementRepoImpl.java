
package com.finance.repo.movement;

import com.finance.domain.movement.Movement;
import com.finance.repo.filter.MovementFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class MovementRepoImpl implements MovementRepoQuery {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public Page<MovFilterDTO> filter(MovementFilter movementFilter, Pageable pageable) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<MovFilterDTO> criteria = builder.createQuery(MovFilterDTO.class);
    Root<Movement> root = criteria.from(Movement.class);

    criteria.select(builder.construct(MovFilterDTO.class,
            root.get("id"),
            root.get("date"),
            root.get("description"),
            root.get("category").get("name"),
            root.get("value"),
            root.get("type").get("name"),
            root.get("user").get("name")));

    Predicate[] predicates = createRestrictions(movementFilter, builder, root);
    criteria.where(predicates);
    criteria.orderBy(
            builder.asc(root.get("date")),
            builder.asc(root.get("category").get("name")),
            builder.asc(root.get("type").get("name")),
            builder.asc(root.get("user").get("name")));

    TypedQuery<MovFilterDTO> query = manager.createQuery(criteria);
    addRestrictionsOfPagination(query, pageable);

    return new PageImpl<>(query.getResultList(), pageable, total(movementFilter));
  }

  private long total(MovementFilter movementFilter) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<Movement> root = criteria.from(Movement.class);

    Predicate[] predicates = createRestrictions(movementFilter, builder, root);
    criteria.where(predicates);

    criteria.select(builder.count(root));

    return manager.createQuery(criteria).getSingleResult();
  }

  private void addRestrictionsOfPagination(TypedQuery<MovFilterDTO> query, Pageable pageable) {
    int currentPage = pageable.getPageNumber();
    int totalRegistersPerPage = pageable.getPageSize();
    int firstRegisterOfPage = currentPage * totalRegistersPerPage;

    query.setFirstResult(firstRegisterOfPage);
    query.setMaxResults(totalRegistersPerPage);
  }

  private Predicate[] createRestrictions(MovementFilter movementFilter, CriteriaBuilder builder, Root<Movement> root) {
    List<Predicate> predicates = new ArrayList<>();

    if (movementFilter.getDate_init() != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get("date"), movementFilter.getDate_init()));
    }
    if (movementFilter.getDate_end() != null) {
      predicates.add(builder.lessThanOrEqualTo(root.get("date"), movementFilter.getDate_end()));
    }

    if (!StringUtils.isEmpty(movementFilter.getCategory())) {
      predicates.add(builder.like(builder.lower(root.get("category").get("name")), "%" + movementFilter.getCategory().toLowerCase() + "%"));
    }

    if (!StringUtils.isEmpty(movementFilter.getType())) {
      predicates.add(builder.like(builder.lower(root.get("type").get("name")), "%" + movementFilter.getType().toLowerCase() + "%"));
    }

    if (!StringUtils.isEmpty(movementFilter.getUser())) {
      predicates.add(builder.like(builder.lower(root.get("user").get("name")), "%" + movementFilter.getUser().toLowerCase() + "%"));
    }

    return predicates.toArray(new Predicate[predicates.size()]);
  }
}
