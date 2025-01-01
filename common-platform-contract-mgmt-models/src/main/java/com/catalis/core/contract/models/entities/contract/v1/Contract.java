package com.catalis.core.contract.models.entities.contract.v1;

import com.catalis.core.contract.interfaces.enums.contract.v1.ContractStatusEnum;
import com.catalis.core.contract.interfaces.enums.contract.v1.ContractTypeEnum;
import com.catalis.core.contract.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("contract")
public class Contract extends BaseEntity {

    @Id
    @Column("contract_id")
    private Long contractId;

    @Column("contract_number")
    private String contractNumber;

    @Column("product_id")
    private Long productId;

    @Column("contract_type")
    private ContractTypeEnum contractType;

    @Column("contract_status")
    private ContractStatusEnum contractStatus;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;

    @Column("document_manager_ref")
    private String documentManagerRef;

}
