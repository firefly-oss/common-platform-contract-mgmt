package com.firefly.core.contracts.models.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Contract term dynamic entity representing dynamic/parameterized terms for contracts
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_term_dynamic")
public class ContractTermDynamic {

    @Id
    @Column("term_id")
    private Long termId;

    @Column("contract_id")
    private Long contractId;

    @Column("term_template_id")
    private Long termTemplateId;

    @Column("term_value_text")
    private String termValueText;

    @Column("term_value_numeric")
    private BigDecimal termValueNumeric;

    @Column("term_value_json")
    private JsonNode termValueJson;

    @Column("effective_date")
    private LocalDateTime effectiveDate;

    @Column("expiration_date")
    private LocalDateTime expirationDate;

    @Column("is_active")
    private Boolean isActive;

    @Column("notes")
    private String notes;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
