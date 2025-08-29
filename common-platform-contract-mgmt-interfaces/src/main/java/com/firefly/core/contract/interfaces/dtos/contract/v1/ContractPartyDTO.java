package com.firefly.core.contract.interfaces.dtos.contract.v1;

import com.firefly.annotations.ValidDate;
import com.firefly.core.contract.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
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
public class ContractPartyDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractPartyId;

    @FilterableId
    private Long contractId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long roleInContractId;

    @ValidDate
    private LocalDateTime dateJoined;
    @ValidDate
    private LocalDateTime dateLeft;
    private Boolean active;

}
