package com.firefly.core.contracts.models.entities;

import com.firefly.core.contracts.interfaces.enums.ContractStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

/**
 * Contract entity representing the main contract table
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract")
public class Contract {

    @Id
    @Column("contract_id")
    private Long contractId;

    @Column("contract_number")
    private String contractNumber;

    @Column("contract_status")
    private ContractStatusEnum contractStatus;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;

    @Column("document_manager_ref_id")
    private Long documentManagerRefId;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
