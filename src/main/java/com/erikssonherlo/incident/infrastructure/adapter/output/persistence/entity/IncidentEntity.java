package com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity;

import com.erikssonherlo.common.application.anotation.PersistenceEntity;
import com.erikssonherlo.incident.domain.model.enums.IncidentStatus;
import com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity.IncidentDetailEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incident")
@SQLDelete(sql = "UPDATE incident SET deleted_at = NOW() WHERE incident_id = ?")
@Where(clause = "deleted_at IS NULL")
@PersistenceEntity
public class IncidentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "incident_id")
        private Integer incidentId;

        @Column(name = "shipment_id", nullable = false)
        private Integer shipmentId;

        @Column(name = "store_id", nullable = false)
        private Integer storeId;

        @Column(name = "user_email", nullable = false)
        private String userEmail;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private IncidentStatus status;

        @Column(name = "solution")
        private String solution;

        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;

        @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<IncidentDetailEntity> incidentDetails;

        @PrePersist
        protected void onCreate() {
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
                this.updatedAt = LocalDateTime.now();
        }
}
