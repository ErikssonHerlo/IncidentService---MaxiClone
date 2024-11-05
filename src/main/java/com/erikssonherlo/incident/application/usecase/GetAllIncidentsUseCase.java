package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.port.input.GetAllIncidentsInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllIncidentsUseCase implements GetAllIncidentsInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;

    @Override
    public Page<Incident> getAllIncidents(Pageable pageable, List<Long> storeIds, String status) {
        try {
            if (storeIds != null && !storeIds.isEmpty()) {
                if (status != null && !status.isEmpty()) {
                    IncidentStatus incidentStatus = IncidentStatus.valueOf(status.toUpperCase());
                    return incidentRepositoryPort.findAllIncidentsByStoreIdAndStatus(pageable, storeIds, incidentStatus);
                }
                return incidentRepositoryPort.findAllIncidentsByStoreId(pageable, storeIds);
            }

            if(status != null && !status.isEmpty()) {
                IncidentStatus incidentStatus = IncidentStatus.valueOf(status.toUpperCase());
                return incidentRepositoryPort.findAllIncidentsByStatus(incidentStatus, pageable);
            }

            return incidentRepositoryPort.findAllIncidents(pageable);

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: "
                    + String.join(", ", IncidentStatus.names()));
        }
    }
}
