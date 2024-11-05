package com.erikssonherlo.incident.application.dto;

import com.erikssonherlo.incident.domain.model.enums.TemplateType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

        @NotNull(message = "Subject is mandatory")
        private String subject;

        @NotNull(message = "To mandatory")
        private String to;

        @NotNull(message = "Message is mandatory")
        private String message;

        @NotNull(message = "Template is mandatory")
        private TemplateType template;

}
