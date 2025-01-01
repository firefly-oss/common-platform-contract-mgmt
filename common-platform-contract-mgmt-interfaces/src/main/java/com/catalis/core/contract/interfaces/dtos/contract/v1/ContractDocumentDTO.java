package com.catalis.core.contract.interfaces.dtos.contract.v1;

import com.catalis.core.contract.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDocumentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractDocumentId;

    private Long contractId;
    private String documentType;
    private String documentManagerRef;
    private LocalDateTime dateAdded;

}
