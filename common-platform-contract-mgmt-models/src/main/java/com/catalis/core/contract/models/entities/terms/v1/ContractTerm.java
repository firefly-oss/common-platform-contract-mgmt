package com.catalis.core.contract.models.entities.terms.v1;

import com.catalis.core.contract.interfaces.enums.terms.v1.TermTypeEnum;
import com.catalis.core.contract.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("contract_term")
public class ContractTerm extends BaseEntity {

    @Id
    @Column("contract_term_id")
    private Long contractTermId;

    @Column("contract_id")
    private Long contractId;

    @Column("term_type")
    private TermTypeEnum termType;

    @Column("term_description")
    private String termDescription;

    @Column("numeric_value")
    private BigDecimal numericValue;

    @Column("value_unit")
    private String valueUnit;

    @Column("effective_date")
    private LocalDateTime effectiveDate;

    @Column("expiration_date")
    private LocalDateTime expirationDate;

}