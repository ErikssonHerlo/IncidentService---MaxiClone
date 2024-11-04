package com.erikssonherlo.incident.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDetail {

    private Integer incidentDetailId;        // ID del detalle de la incidencia
    private Integer incidentId;              // ID de la incidencia (relación con Incident)
    private String productSku;               // SKU del producto afectado
    private Integer affectedQuantity;        // Cantidad afectada
    private String reason;                   // Motivo de la incidencia
    private LocalDateTime createdAt;         // Fecha de creación
    private LocalDateTime updatedAt;         // Fecha de última actualización
    private LocalDateTime deletedAt;         // Fecha de eliminación (para soft delete)
}
