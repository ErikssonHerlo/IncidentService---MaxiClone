package com.erikssonherlo.incident.domain.model;

import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.domain.model.IncidentDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    private Integer incidentId;
    private Integer shipmentId;              // ID de envío
    private Integer storeId;                 // ID de tienda
    private String userEmail;                // Correo del usuario que reporta la incidencia
    private IncidentStatus status;                   // Estado de la incidencia (OPEN, CLOSED)
    private String solution;                 // Solución al problema, si existe
    private LocalDateTime createdAt;         // Fecha de creación
    private LocalDateTime updatedAt;         // Fecha de última actualización
    private LocalDateTime deletedAt;         // Fecha de eliminación (para soft delete)
    private List<IncidentDetail> details;    // Lista de detalles de la incidencia
}
