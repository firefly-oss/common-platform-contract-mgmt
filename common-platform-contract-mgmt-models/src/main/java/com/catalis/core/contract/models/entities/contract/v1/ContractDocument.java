package com.catalis.core.contract.models.entities.contract.v1;

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
@Table("contract_document")
public class ContractDocument extends BaseEntity {
    @Id
    @Column("contract_document_id")
    private Long contractDocumentId;

    @Column("contract_id")
    private Long contractId;

    @Column("document_type")
    private String documentType;

    @Column("document_manager_ref")
    private String documentManagerRef;

    @Column("date_added")
    private LocalDateTime dateAdded;

}
