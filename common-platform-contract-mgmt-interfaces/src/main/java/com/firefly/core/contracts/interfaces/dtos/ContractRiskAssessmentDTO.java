package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.contracts.interfaces.enums.RiskLevelEnum;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Contract risk assessment DTO for API operations - compatible with R2DBC ContractRiskAssessment entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRiskAssessmentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractRiskAssessmentId;

    @NotNull(message = "Contract ID is required")
    private Long contractId;

    @DecimalMin(value = "0.00", message = "Risk score must be greater than or equal to 0")
    @DecimalMax(value = "100.00", message = "Risk score must be less than or equal to 100")
    private BigDecimal riskScore;

    @NotNull(message = "Risk level is required")
    private RiskLevelEnum riskLevel;

    @ValidDateTime
    private LocalDateTime assessmentDate;

    @Size(max = 255, message = "Assessor must not exceed 255 characters")
    private String assessor;

    private String notes;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
