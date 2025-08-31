package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.contracts.interfaces.enums.TermValidationTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contract term validation rule DTO for API operations - compatible with R2DBC ContractTermValidationRule entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractTermValidationRuleDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long validationRuleId;

    @NotNull(message = "Term template ID is required")
    private Long termTemplateId;

    @NotNull(message = "Validation type is required")
    private TermValidationTypeEnum validationType;

    @Size(max = 1000, message = "Validation value must not exceed 1000 characters")
    private String validationValue;

    @Size(max = 500, message = "Error message must not exceed 500 characters")
    private String errorMessage;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
