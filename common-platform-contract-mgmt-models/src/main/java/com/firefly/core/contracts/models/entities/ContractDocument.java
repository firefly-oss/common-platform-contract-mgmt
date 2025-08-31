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

/**
 * Contract document entity representing documents associated with a contract
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_document")
public class ContractDocument {

    @Id
    @Column("contract_document_id")
    private Long contractDocumentId;

    @Column("contract_id")
    private Long contractId;

    @Column("document_type_id")
    private Long documentTypeId;

    @Column("document_id")
    private Long documentId;

    @Column("date_added")
    private LocalDateTime dateAdded;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
