package com.erikssonherlo.incident.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceAlreadyExistsException;
import com.erikssonherlo.common.application.exception.ResourceNotFoundException;
import com.erikssonherlo.common.application.security.JWTService;
import com.erikssonherlo.incident.application.dto.CreateIncidentDTO;
import com.erikssonherlo.incident.application.dto.UpdateIncidentDTO;
import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.infrastructure.port.input.UpdateIncidentInputPort;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateIncidentUseCase implements UpdateIncidentInputPort {
    private final IncidentJpaRepositoryPort incidentRepositoryPort;
    private final JWTService jwtService;
    private  final HttpServletRequest request;

    @Override
    public Incident updateIncident(Long id, UpdateIncidentDTO updateIncidentDTO) {
        try {
            String token = jwtService.extractToken(request);
            Optional<Incident> incident = incidentRepositoryPort.find(id);
            if(incident.isEmpty()) throw new ResourceNotFoundException("incident","id",id);
            incident.get().setStatus(updateIncidentDTO.status());
            incident.get().setSolution(updateIncidentDTO.solution());


            return incidentRepositoryPort.updateIncident(id, incident.get());

        }catch (Exception e){
            throw e;
        }
    }
}
