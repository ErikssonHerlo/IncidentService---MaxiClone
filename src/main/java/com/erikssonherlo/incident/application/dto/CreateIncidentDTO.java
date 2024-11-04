package com.erikssonherlo.incident.application.dto;

import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateIncidentDTO(
        @NotNull(message = "Shipment ID is mandatory")
        Integer shipmentId,

        @NotNull(message = "Store ID is mandatory")
        Integer storeId,

        @Email(message = "User email must be valid")
        @NotBlank(message = "User email is mandatory")
        String userEmail,

        @NotNull(message = "Status is mandatory")
        IncidentStatus status,

        String solution, // optional field for solution

        @NotEmpty(message = "Incident details cannot be empty")
        List<CreateIncidentDetailDTO> details // List of detail records
) {}
