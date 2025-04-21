package com.catalis.core.contract.interfaces.dtos.risk.v1;

import com.catalis.core.contract.interfaces.dtos.BaseDTO;
import com.catalis.core.contract.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.utils.annotations.FilterableId;
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

    private BigDecimal riskScore;
    private RiskLevelEnum riskLevel;
    private LocalDateTime assessmentDate;
    private String assessor;
    private String notes;

}