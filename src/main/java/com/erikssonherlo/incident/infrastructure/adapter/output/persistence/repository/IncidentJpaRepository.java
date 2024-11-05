package com.erikssonherlo.incident.infrastructure.adapter.output.persistence.repository;

import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity.IncidentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentJpaRepository extends JpaRepository<IncidentEntity, Long> {

    // Encuentra incidentes por estado de forma paginada
    Page<IncidentEntity> findByStatus(IncidentStatus status, Pageable pageable);

    Page<IncidentEntity> findByStoreIdIn(List<Long> storeIds, Pageable pageable);

    Page<IncidentEntity> findAllByStoreIdInAndStatus(List<Long> storeIds, IncidentStatus status, Pageable pageable);
}
