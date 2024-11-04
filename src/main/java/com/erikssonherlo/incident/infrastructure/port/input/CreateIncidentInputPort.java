package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.application.dto.CreateIncidentDTO;
import com.erikssonherlo.incident.domain.model.Incident;

public interface CreateIncidentInputPort {
    Incident createIncident(CreateIncidentDTO createIncidentDto);
}
