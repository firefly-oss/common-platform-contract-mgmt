package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.annotations.ValidDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contract document DTO for API operations - compatible with R2DBC ContractDocument entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDocumentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractDocumentId;

    @NotNull(message = "Contract ID is required")
    private Long contractId;

    @NotNull(message = "Document type ID is required")
    private Long documentTypeId;

    @NotNull(message = "Document ID is required")
    private Long documentId;

    @ValidDateTime
    private LocalDateTime dateAdded;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
