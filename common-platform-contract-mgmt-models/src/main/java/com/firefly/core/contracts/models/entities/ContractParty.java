package com.firefly.core.contracts.models.entities;

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
 * Contract party entity representing parties linked to a contract
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_party")
public class ContractParty {

    @Id
    @Column("contract_party_id")
    private UUID contractPartyId;

    @Column("contract_id")
    private UUID contractId;

    @Column("party_id")
    private UUID partyId;

    @Column("role_in_contract_id")
    private UUID roleInContractId;

    @Column("date_joined")
    private LocalDateTime dateJoined;

    @Column("date_left")
    private LocalDateTime dateLeft;

    @Column("is_active")
    private Boolean isActive;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
