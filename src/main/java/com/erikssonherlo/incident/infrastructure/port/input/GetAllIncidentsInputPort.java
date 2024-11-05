package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.domain.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetAllIncidentsInputPort {
    Page<Incident> getAllIncidents(Pageable pageable, List<Long> storeId, String status);
}
