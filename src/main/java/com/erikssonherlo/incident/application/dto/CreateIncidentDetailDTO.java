package com.erikssonherlo.incident.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateIncidentDetailDTO(
        @NotBlank(message = "Product SKU is mandatory")
        String productSku,

        @NotNull(message = "Affected quantity is mandatory")
        @Positive(message = "Affected quantity must be positive")
        Integer affectedQuantity,

        @NotBlank(message = "Reason is mandatory")
        String reason
) {}
