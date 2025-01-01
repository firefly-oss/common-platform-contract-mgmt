package com.catalis.core.contract.interfaces.dtos.contract.v1;

import com.catalis.core.contract.interfaces.dtos.BaseDTO;
import com.catalis.core.contract.interfaces.enums.contract.v1.ContractStatusEnum;
import com.catalis.core.contract.interfaces.enums.contract.v1.ContractTypeEnum;
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

    private String contractNumber;
    private Long productId;
    private ContractTypeEnum contractType;
    private ContractStatusEnum contractStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String documentManagerRef;

}
