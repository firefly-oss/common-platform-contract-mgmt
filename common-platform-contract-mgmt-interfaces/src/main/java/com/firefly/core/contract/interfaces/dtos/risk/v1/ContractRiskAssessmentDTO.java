package com.firefly.core.contract.interfaces.dtos.risk.v1;

import com.firefly.annotations.ValidDate;
import com.firefly.annotations.ValidInterestRate;
import com.firefly.core.contract.interfaces.dtos.BaseDTO;
import com.firefly.core.contract.interfaces.enums.risk.v1.RiskLevelEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRiskAssessmentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractRiskAssessmentId;

    @FilterableId
    private Long contractId;

    @ValidInterestRate
    private BigDecimal riskScore;

    private RiskLevelEnum riskLevel;

    @ValidDate
    private LocalDateTime assessmentDate;

    private String assessor;
    private String notes;
}
