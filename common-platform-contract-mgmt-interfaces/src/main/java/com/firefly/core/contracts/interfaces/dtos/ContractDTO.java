package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.contracts.interfaces.enums.ContractStatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contract DTO for API operations - compatible with R2DBC Contract entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractId;

    @Size(max = 255, message = "Contract number must not exceed 255 characters")
    private String contractNumber;

    @NotNull(message = "Contract status is required")
    private ContractStatusEnum contractStatus;

    @ValidDateTime
    private LocalDateTime startDate;

    @ValidDateTime
    private LocalDateTime endDate;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
