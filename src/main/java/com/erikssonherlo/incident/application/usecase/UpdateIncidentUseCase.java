package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceAlreadyExistsException;
import com.erikssonherlo.common.application.exception.ResourceNotFoundException;
import com.erikssonherlo.common.application.security.JWTService;
import com.erikssonherlo.incident.application.dto.CreateIncidentDTO;
import com.erikssonherlo.incident.application.dto.NotificationDto;
import com.erikssonherlo.incident.application.dto.UpdateIncidentDTO;
import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.enums.TemplateType;
import com.erikssonherlo.incident.infrastructure.port.input.UpdateIncidentInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateIncidentUseCase implements UpdateIncidentInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;
    private final JWTService jwtService;
    private  final HttpServletRequest request;
    private final NotificationDto notificationDto = new NotificationDto();
    private final RabbitTemplate rabbitTemplate;
    private final String queueName = "notifications";

    @Override
    @Transactional
    public Incident updateIncident(Long id, UpdateIncidentDTO updateIncidentDTO) {
        try {
            String token = jwtService.extractToken(request);
            Optional<Incident> incident = incidentRepositoryPort.find(id);
            if(incident.isEmpty()) throw new ResourceNotFoundException("incident","id",id);
            incident.get().setStatus(updateIncidentDTO.status());
            incident.get().setSolution(updateIncidentDTO.solution());
            notificationDto.setTo(incident.get().getUserEmail());
            notificationDto.setSubject("YOUR INCIDENT #" + incident.get().getIncidentId() + " UPDATED");
            notificationDto.setTemplate(TemplateType.INCIDENT_CLOSED);
            notificationDto.setMessage(incident.get().getSolution());
            rabbitTemplate.convertAndSend(queueName, notificationDto);
            System.out.println("Email enviado a la cola: " + notificationDto);
            return incidentRepositoryPort.updateIncident(id, incident.get());
        }catch (Exception e){
            throw e;
        }
    }
}
