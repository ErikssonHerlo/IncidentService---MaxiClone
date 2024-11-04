package com.erikssonherlo.incident.infrastructure.adapter.output.persistence.entity;

import com.erikssonherlo.common.application.anotation.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incident_detail")
@SQLDelete(sql = "UPDATE incident_detail SET deleted_at = NOW() WHERE incident_detail_id = ?")
@Where(clause = "deleted_at IS NULL")
@PersistenceEntity
public class IncidentDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_detail_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incident_id", nullable = false)
    private IncidentEntity incident;

    @Column(name = "product_sku", nullable = false)
    private String productSku;

    @Column(name = "affected_quantity", nullable = false)
    private Integer affectedQuantity;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

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
