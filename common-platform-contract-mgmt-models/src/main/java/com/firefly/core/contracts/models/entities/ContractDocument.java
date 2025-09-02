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
    private UUID contractDocumentId;

    @Column("contract_id")
    private UUID contractId;

    @Column("document_type_id")
    private UUID documentTypeId;

    @Column("document_id")
    private UUID documentId;

    @Column("date_added")
    private LocalDateTime dateAdded;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
