package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.port.input.GetAllIncidentsByStatusInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetAllIncidentsByStatusUseCase implements GetAllIncidentsByStatusInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;

    @Override
    public Page<Incident> getAllIncidentsByStatus(String status, Pageable pageable) {
        try {
            IncidentStatus incidentStatus = IncidentStatus.valueOf(status.toUpperCase());

            return incidentRepositoryPort.findAllIncidentsByStatus(incidentStatus, pageable);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: "
                    + String.join(", ", IncidentStatus.names()));
        }
    }
}
