package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.firefly.annotations.ValidDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Contract term dynamic DTO for API operations - compatible with R2DBC ContractTermDynamic entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractTermDynamicDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long termId;

    @NotNull(message = "Contract ID is required")
    private Long contractId;

    @NotNull(message = "Term template ID is required")
    private Long termTemplateId;

    private String termValueText;

    private BigDecimal termValueNumeric;

    private JsonNode termValueJson;

    @NotNull(message = "Effective date is required")
    @ValidDateTime
    private LocalDateTime effectiveDate;

    @ValidDateTime
    private LocalDateTime expirationDate;

    private Boolean isActive;

    private String notes;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
