package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contract party DTO for API operations - compatible with R2DBC ContractParty entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractPartyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractPartyId;

    @NotNull(message = "Contract ID is required")
    private Long contractId;

    @NotNull(message = "Party ID is required")
    private Long partyId;

    @NotNull(message = "Role in contract ID is required")
    private Long roleInContractId;

    @ValidDateTime
    private LocalDateTime dateJoined;

    @ValidDateTime
    private LocalDateTime dateLeft;

    private Boolean isActive;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
