package com.catalis.core.contract.interfaces.dtos.contract.v1;

import com.catalis.annotations.ValidDate;
import com.catalis.core.contract.interfaces.dtos.BaseDTO;
import com.catalis.core.contract.interfaces.enums.contract.v1.ContractStatusEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class ContractDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractId;

    private String contractNumber; // customer-specific contract number

    @FilterableId
    private Long productCatalogId; // product catalog identifier

    @FilterableId
    private Long productId; // product specific identifier

    private ContractStatusEnum contractStatus;

    @ValidDate
    private LocalDateTime startDate;

    @ValidDate
    private LocalDateTime endDate;

    private String documentManagerRef;

}