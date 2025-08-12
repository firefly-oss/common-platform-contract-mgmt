package com.catalis.core.contract.interfaces.dtos.terms.v1;

import com.catalis.annotations.ValidDate;
import com.catalis.core.contract.interfaces.dtos.BaseDTO;
import com.catalis.core.contract.interfaces.enums.terms.v1.TermTypeEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContractTermDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractTermId;

    @FilterableId
    private Long contractId;

    private TermTypeEnum termType;
    private String termDescription;

    private BigDecimal numericValue;
    private String valueUnit;
    @ValidDate
    private LocalDateTime effectiveDate;
    @ValidDate
    private LocalDateTime expirationDate;

}
