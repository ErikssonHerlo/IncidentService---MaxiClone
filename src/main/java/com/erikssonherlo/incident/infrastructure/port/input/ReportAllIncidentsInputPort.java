package com.erikssonherlo.incident.infrastructure.port.input;

import com.erikssonherlo.incident.domain.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportAllIncidentsInputPort {
    List<Incident> reportAllIncidents(List<Long> storeId, String status,String startDate, String endDate);
}
