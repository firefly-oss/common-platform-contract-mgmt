package com.firefly.core.contracts.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.contracts.interfaces.enums.TermCategoryEnum;
import com.firefly.core.contracts.interfaces.enums.TermDataTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Contract term template DTO for API operations - compatible with R2DBC ContractTermTemplate entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractTermTemplateDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long termTemplateId;

    @NotBlank(message = "Code is required")
    @Size(max = 100, message = "Code must not exceed 100 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Term category is required")
    private TermCategoryEnum termCategory;

    @NotNull(message = "Data type is required")
    private TermDataTypeEnum dataType;

    private Boolean isRequired;

    private Boolean isActive;

    @Size(max = 500, message = "Default value must not exceed 500 characters")
    private String defaultValue;

    private JsonNode validationRules;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @ValidDateTime
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
