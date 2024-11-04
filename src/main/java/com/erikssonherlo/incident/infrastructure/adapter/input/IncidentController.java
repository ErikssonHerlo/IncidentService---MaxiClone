package com.erikssonherlo.incident.infrastructure.adapter.input;

import com.erikssonherlo.common.application.response.ApiResponse;
import com.erikssonherlo.common.application.response.PaginatedResponse;
import com.erikssonherlo.incident.application.dto.CreateIncidentDTO;
import com.erikssonherlo.incident.application.dto.UpdateIncidentDTO;
import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.infrastructure.port.input.*;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final FindIncidentInputPort findIncidentInputPort;
    private final DeleteIncidentInputPort deleteIncidentInputPort;
    private final CreateIncidentInputPort createIncidentInputPort;
    private final UpdateIncidentInputPort updateIncidentInputPort;
    private final GetAllIncidentsInputPort getAllIncidentsInputPort;
    private final GetAllIncidentsByStatusInputPort getAllIncidentsByStatusInputPort;

    @GetMapping()
    public PaginatedResponse<List<Incident>> getAllIncidents(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Incident> incidentsPage = getAllIncidentsInputPort.getAllIncidents(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"SUCCESS", HttpStatus.OK,incidentsPage.getContent(),incidentsPage.getPageable(),incidentsPage.isLast(),incidentsPage.isFirst(),incidentsPage.hasNext(),incidentsPage.hasPrevious(),incidentsPage.getTotalPages(),(int) incidentsPage.getTotalElements());
    }

    @GetMapping("/status/{status}")
    public PaginatedResponse<List<Incident>> getAllIncidentsByStatus(@PathVariable String status, @PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Incident> incidentsPage = getAllIncidentsByStatusInputPort.getAllIncidentsByStatus(status,pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"SUCCESS", HttpStatus.OK,incidentsPage.getContent(),incidentsPage.getPageable(),incidentsPage.isLast(),incidentsPage.isFirst(),incidentsPage.hasNext(),incidentsPage.hasPrevious(),incidentsPage.getTotalPages(),(int) incidentsPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public ApiResponse<Incident> findIncident(@PathVariable Long id){
        Incident incident = findIncidentInputPort.findIncident(id);
        return new ApiResponse<>(HttpStatus.OK.value(),"SUCCESS", HttpStatus.OK,incident);
    }

    @PostMapping()
    public ApiResponse<Incident> createIncident(@RequestBody @Valid CreateIncidentDTO createIncidentDTO){
        return new ApiResponse<>(HttpStatus.CREATED.value(),"SUCCESS",HttpStatus.CREATED,createIncidentInputPort.createIncident(createIncidentDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse<Incident> updateIncident(@PathVariable Long id, @RequestBody @Valid UpdateIncidentDTO updateIncidentDTO){
        return new ApiResponse<>(HttpStatus.OK.value(),"SUCCESS",HttpStatus.OK, updateIncidentInputPort.updateIncident(id,updateIncidentDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteIncident(@PathVariable Long id){
        return new ApiResponse<>(HttpStatus.NO_CONTENT.value(),"SUCCESS",HttpStatus.NO_CONTENT,deleteIncidentInputPort.deleteIncident(id));
    }
}
