package com.firefly.core.contracts.models.entities;

import com.firefly.core.contracts.interfaces.enums.StatusCodeEnum;
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
import java.util.UUID;

/**
 * Contract status history entity representing the status timeline of a contract
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_status_history")
public class ContractStatusHistory {

    @Id
    @Column("contract_status_history_id")
    private UUID contractStatusHistoryId;

    @Column("contract_id")
    private UUID contractId;

    @Column("status_code")
    private StatusCodeEnum statusCode;

    @Column("status_start_date")
    private LocalDateTime statusStartDate;

    @Column("status_end_date")
    private LocalDateTime statusEndDate;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
