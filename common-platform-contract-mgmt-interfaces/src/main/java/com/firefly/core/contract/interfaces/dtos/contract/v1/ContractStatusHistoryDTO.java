package com.firefly.core.contract.interfaces.dtos.contract.v1;

import com.firefly.annotations.ValidDate;
import com.firefly.core.contract.interfaces.dtos.BaseDTO;
import com.firefly.core.contract.interfaces.enums.contract.v1.StatusCodeEnum;
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
public class ContractStatusHistoryDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractStatusHistoryId;

    @FilterableId
    private Long contractId;

    private StatusCodeEnum statusCode;

    @ValidDate
    private LocalDateTime statusStartDate;

    @ValidDate
    private LocalDateTime statusEndDate;

}
