package com.catalis.core.contract.models.entities.risk.v1;

import com.catalis.core.contract.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.contract.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("contract_risk_assessment")
public class ContractRiskAssessment extends BaseEntity {

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

}
