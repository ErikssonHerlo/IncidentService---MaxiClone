package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllIncidentsByStatusInputPort {
    Page<Incident> getAllIncidentsByStatus(String status, Pageable pageable);
}
