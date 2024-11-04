package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.application.dto.CreateIncidentDTO;
import com.erikssonherlo.incident.application.dto.UpdateIncidentDTO;
import com.erikssonherlo.incident.domain.model.Incident;

public interface UpdateIncidentInputPort {
    Incident updateIncident(Long id, UpdateIncidentDTO updateIncidentDTO);
}
