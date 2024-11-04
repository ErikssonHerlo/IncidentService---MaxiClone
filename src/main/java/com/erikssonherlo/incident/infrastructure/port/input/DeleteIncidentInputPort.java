package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.domain.model.Incident;

public interface DeleteIncidentInputPort {
    Incident deleteIncident(Long id);
}
