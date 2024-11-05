package com.erikssonherlo.incident.infrastructure.port.output.db;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity.IncidentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IncidentJpaRepositoryPort {

    Optional<Incident> find(Long id);

    Optional<IncidentEntity> findEntity(Long id);

    Boolean existsIncident (Long id);

    Page<Incident> findAllIncidents(Pageable pageable);

    List<Incident> reportAllIncidents(List<Long> storeIds, String status, String startDate, String endDate);

    Page<Incident> findAllIncidentsByStatus(IncidentStatus status, Pageable pageable);

    Page<Incident> findAllIncidentsByStoreId(Pageable pageable, List<Long> storeIds);

    Page<Incident> findAllIncidentsByStoreIdAndStatus(Pageable pageable, List<Long> storeIds, IncidentStatus status);

    Incident saveIncident(Incident incident);

    Incident updateIncident(Long id, Incident incident);

    void deleteIncident(Long id);
}
