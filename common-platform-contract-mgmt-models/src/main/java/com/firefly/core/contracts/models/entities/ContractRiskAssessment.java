package com.firefly.core.contracts.models.entities;

import com.firefly.core.contracts.interfaces.enums.RiskLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Contract risk assessment entity representing risk evaluations for contracts
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_risk_assessment")
public class ContractRiskAssessment {

    @Id
    @Column("contract_risk_assessment_id")
    private Long contractRiskAssessmentId;

    @Column("contract_id")
    private Long contractId;

    @Column("risk_score")
    private BigDecimal riskScore;

    @Column("risk_level")
    private RiskLevelEnum riskLevel;

    @Column("assessment_date")
    private LocalDateTime assessmentDate;

    @Column("assessor")
    private String assessor;

    @Column("notes")
    private String notes;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
