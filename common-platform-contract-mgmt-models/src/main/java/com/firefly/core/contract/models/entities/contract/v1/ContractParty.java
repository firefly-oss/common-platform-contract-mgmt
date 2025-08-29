package com.firefly.core.contract.models.entities.contract.v1;

import com.firefly.core.contract.models.entities.BaseEntity;
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
@Table("contract_party")
public class ContractParty extends BaseEntity {

    @Id
    @Column("contract_party_id")
    private Long contractPartyId;

    @Column("contract_id")
    private Long contractId;

    @Column("party_id")
    private Long partyId;

    @Column("role_in_contract")
    private Long roleInContractId;

    @Column("date_joined")
    private LocalDateTime dateJoined;

    @Column("date_left")
    private LocalDateTime dateLeft;

    @Column("is_active")
    private Boolean active;

}
