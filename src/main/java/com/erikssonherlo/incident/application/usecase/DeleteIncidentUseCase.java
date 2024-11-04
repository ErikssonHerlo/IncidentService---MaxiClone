package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceNotFoundException;
import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.infrastructure.port.input.DeleteIncidentInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteIncidentUseCase implements DeleteIncidentInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;

    @Override
    public Incident deleteIncident(Long id) throws ResourceNotFoundException {
        Optional<Incident> incident = incidentRepositoryPort.find(id);
        if(incident.isEmpty()) throw new ResourceNotFoundException("incident","id",id);
        incidentRepositoryPort.deleteIncident(id);
        return null;
    }
}
