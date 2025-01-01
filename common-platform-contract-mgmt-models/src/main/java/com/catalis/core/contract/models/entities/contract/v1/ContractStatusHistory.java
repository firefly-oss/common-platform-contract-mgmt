package com.catalis.core.contract.models.entities.contract.v1;

import com.catalis.core.contract.interfaces.enums.contract.v1.StatusCodeEnum;
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
@Table("contract_status_history")
public class ContractStatusHistory extends BaseEntity {
    @Id
    @Column("contract_status_history_id")
    private Long contractStatusHistoryId;

    @Column("contract_id")
    private Long contractId;

    @Column("status_code")
    private StatusCodeEnum statusCode;

    @Column("status_start_date")
    private LocalDateTime statusStartDate;

    @Column("status_end_date")
    private LocalDateTime statusEndDate;
}
