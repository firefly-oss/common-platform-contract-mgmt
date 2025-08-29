package com.firefly.core.contract.interfaces.dtos;

import com.firefly.annotations.ValidDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ValidDate
    private LocalDateTime dateCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ValidDate
    private LocalDateTime dateUpdated;
}
