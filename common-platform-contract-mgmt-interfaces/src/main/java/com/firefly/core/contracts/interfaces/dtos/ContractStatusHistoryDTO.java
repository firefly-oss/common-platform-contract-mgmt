package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.contracts.interfaces.enums.StatusCodeEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Contract status history DTO for API operations - compatible with R2DBC ContractStatusHistory entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractStatusHistoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID contractStatusHistoryId;

    @NotNull(message = "Contract ID is required")
    private UUID contractId;

    @NotNull(message = "Status code is required")
    private StatusCodeEnum statusCode;

    @NotNull(message = "Status start date is required")
    @ValidDateTime
    private LocalDateTime statusStartDate;

    @ValidDateTime
    private LocalDateTime statusEndDate;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
