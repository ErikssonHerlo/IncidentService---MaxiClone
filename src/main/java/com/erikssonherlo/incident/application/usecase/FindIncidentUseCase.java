package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceNotFoundException;
import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.infrastructure.port.input.FindIncidentInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindIncidentUseCase implements FindIncidentInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;

    @Override
    public Incident findIncident(Long id) throws ResourceNotFoundException {
        Optional<Incident> incident = incidentRepositoryPort.find(id);
        if(incident.isEmpty()) throw new ResourceNotFoundException("incident","id",id);
        return incident.get();
    }
}
