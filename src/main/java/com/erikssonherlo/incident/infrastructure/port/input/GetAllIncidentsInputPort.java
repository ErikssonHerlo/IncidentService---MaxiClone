package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.domain.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllIncidentsInputPort {
    Page<Incident> getAllIncidents(Pageable pageable);
}
