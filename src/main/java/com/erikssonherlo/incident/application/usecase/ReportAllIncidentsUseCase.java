package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.port.input.GetAllIncidentsInputPort;
import com.erikssonherlo.incident.infrastructure.port.input.ReportAllIncidentsInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportAllIncidentsUseCase implements ReportAllIncidentsInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;

    @Override
    public List<Incident> reportAllIncidents(List<Long> storeIds, String status, String startDate, String endDate) {
        try {
            return incidentRepositoryPort.reportAllIncidents(storeIds, status, startDate, endDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: "
                    + String.join(", ", IncidentStatus.names()));
        }
    }
}
