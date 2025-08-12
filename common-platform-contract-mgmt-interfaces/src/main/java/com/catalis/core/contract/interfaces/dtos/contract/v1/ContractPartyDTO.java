package com.catalis.core.contract.interfaces.dtos.contract.v1;

import com.catalis.annotations.ValidDate;
import com.catalis.core.contract.interfaces.dtos.BaseDTO;
import com.catalis.core.contract.interfaces.enums.contract.v1.RoleInContractEnum;
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
public class ContractPartyDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractPartyId;

    @FilterableId
    private Long contractId;

    @FilterableId
    private Long partyId;

    private RoleInContractEnum roleInContract;
    @ValidDate
    private LocalDateTime dateJoined;
    @ValidDate
    private LocalDateTime dateLeft;
    private Boolean active;

}
