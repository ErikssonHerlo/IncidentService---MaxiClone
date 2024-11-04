package com.erikssonherlo.incident.application.dto;

import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateIncidentDTO(
      /*  @Email(message = "User email must be valid")
        @NotBlank(message = "User email is mandatory")
        String userEmail,*/

        IncidentStatus status, // Nullable, to update only if necessary

        String solution // Nullable, for adding a solution text

) {}
