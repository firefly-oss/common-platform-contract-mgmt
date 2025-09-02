package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.contracts.interfaces.enums.EventTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Contract event DTO for API operations - compatible with R2DBC ContractEvent entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractEventDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID contractEventId;

    @NotNull(message = "Contract ID is required")
    private UUID contractId;

    @NotNull(message = "Event type is required")
    private EventTypeEnum eventType;

    @ValidDateTime
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime eventDate;

    private String eventDescription;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
