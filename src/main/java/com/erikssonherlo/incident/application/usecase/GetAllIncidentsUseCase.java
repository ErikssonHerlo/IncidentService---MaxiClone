package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.infrastructure.port.input.GetAllIncidentsInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllIncidentsUseCase implements GetAllIncidentsInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;

    @Override
    public Page<Incident> getAllIncidents(Pageable pageable){
        return incidentRepositoryPort.findAllIncidents(pageable);
    }
}
