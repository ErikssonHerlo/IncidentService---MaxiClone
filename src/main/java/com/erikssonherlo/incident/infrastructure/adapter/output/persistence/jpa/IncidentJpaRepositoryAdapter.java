package com.erikssonherlo.incident.infrastructure.adapter.output.persistence.jpa;

import com.erikssonherlo.incident.domain.model.Incident;
import com.erikssonherlo.incident.domain.model.IncidentDetail;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity.IncidentDetailEntity;
import com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity.IncidentEntity;
import com.erikssonherlo.incident.infrastructure.adapter.output.persistence.repository.IncidentJpaRepository;
import com.erikssonherlo.incident.infrastructure.port.output.db.IncidentJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IncidentJpaRepositoryAdapter implements IncidentJpaRepositoryPort {

    private final IncidentJpaRepository incidentJpaRepository;

    @Override
    public Optional<Incident> find(Long id) {
        return incidentJpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<IncidentEntity> findEntity(Long id) {
        return incidentJpaRepository.findById(id);
    }

    @Override
    public Boolean existsIncident(Long id) {
        return incidentJpaRepository.existsById(id);
    }

    @Override
    public Page<Incident> findAllIncidents(Pageable pageable) {
        return incidentJpaRepository.findAll(pageable).map(this::mapToDomain);
    }

    @Override
    public Page<Incident> findAllIncidentsByStatus(IncidentStatus status, Pageable pageable) {
        return incidentJpaRepository.findByStatus(status, pageable).map(this::mapToDomain);
    }

    @Override
    public Page<Incident> findAllIncidentsByStoreIdAndStatus(Pageable pageable, List<Long> storeIds, IncidentStatus status) {
        return incidentJpaRepository.findAllByStoreIdInAndStatus(storeIds, status, pageable)
                .map(this::mapToDomain);
    }

    // Method to get incidents by store IDs (without status filtering)
    @Override
    public Page<Incident> findAllIncidentsByStoreId(Pageable pageable, List<Long> storeIds) {
        return incidentJpaRepository.findByStoreIdIn(storeIds, pageable)
                .map(this::mapToDomain);
    }
    @Override
    public Incident saveIncident(Incident incident) {
        IncidentEntity incidentEntity = mapToEntity(incident, false);
        IncidentEntity savedEntity = incidentJpaRepository.save(incidentEntity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Incident updateIncident(Long id, Incident incident) {
        if (existsIncident(id)) {
            IncidentEntity incidentEntity = mapToEntity(incident, true);
            incidentEntity.setIncidentId(id.intValue());  // Ensure the ID remains consistent

            IncidentEntity updatedEntity = incidentJpaRepository.save(incidentEntity);
            return mapToDomain(updatedEntity);
        } else {
            throw new IllegalArgumentException("Incident with id " + id + " does not exist.");
        }
    }

    @Override
    public void deleteIncident(Long id) {
        incidentJpaRepository.deleteById(id);
    }

    // Helper methods for mapping

    private Incident mapToDomain(IncidentEntity entity) {
        List<IncidentDetail> details = entity.getIncidentDetails().stream()
                .map(detailEntity -> IncidentDetail.builder()
                        .incidentDetailId(detailEntity.getId())
                        .incidentId(detailEntity.getIncident().getIncidentId())
                        .productSku(detailEntity.getProductSku())
                        .affectedQuantity(detailEntity.getAffectedQuantity())
                        .reason(detailEntity.getReason())
                        .createdAt(detailEntity.getCreatedAt())
                        .updatedAt(detailEntity.getUpdatedAt())
                        .deletedAt(detailEntity.getDeletedAt())
                        .build())
                .collect(Collectors.toList());

        return Incident.builder()
                .incidentId(entity.getIncidentId())
                .shipmentId(entity.getShipmentId())
                .storeId(entity.getStoreId())
                .userEmail(entity.getUserEmail())
                .status(entity.getStatus())
                .solution(entity.getSolution())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .details(details)
                .build();
    }

    private IncidentEntity mapToEntity(Incident incident, boolean isUpdate) {
        IncidentEntity entity = IncidentEntity.builder()
                .shipmentId(incident.getShipmentId())
                .storeId(incident.getStoreId())
                .userEmail(incident.getUserEmail())
                .status(incident.getStatus())
                .solution(incident.getSolution())
                .createdAt(incident.getCreatedAt())
                .updatedAt(incident.getUpdatedAt())
                .deletedAt(incident.getDeletedAt())
                .build();

        if (!isUpdate && incident.getDetails() != null) {
            List<IncidentDetailEntity> detailEntities = incident.getDetails().stream()
                .map(detail -> IncidentDetailEntity.builder()
                        .incident(entity)
                        .productSku(detail.getProductSku())
                        .affectedQuantity(detail.getAffectedQuantity())
                        .reason(detail.getReason())
                        .createdAt(detail.getCreatedAt())
                        .updatedAt(detail.getUpdatedAt())
                        .deletedAt(detail.getDeletedAt())
                        .build())
                .collect(Collectors.toList());
            entity.setIncidentDetails(detailEntities);
        } else {
            entity.setIncidentDetails(new ArrayList<>());
        }
        return entity;
    }

}
