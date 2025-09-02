package com.firefly.core.contracts.models.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.firefly.core.contracts.interfaces.enums.TermValidationTypeEnum;
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
 * Contract term validation rule entity representing validation rules for contract term templates
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_term_validation_rule")
public class ContractTermValidationRule {

    @Id
    @Column("validation_rule_id")
    private UUID validationRuleId;

    @Column("term_template_id")
    private UUID termTemplateId;

    @Column("validation_type")
    private TermValidationTypeEnum validationType;

    @Column("validation_value")
    private JsonNode validationValue;

    @Column("error_message")
    private String errorMessage;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
