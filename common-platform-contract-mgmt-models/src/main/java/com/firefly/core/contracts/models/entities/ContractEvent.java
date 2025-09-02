package com.firefly.core.contracts.models.entities;

import com.firefly.core.contracts.interfaces.enums.EventTypeEnum;
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
 * Contract event entity representing events that occur during a contract's lifecycle
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contract_event")
public class ContractEvent {

    @Id
    @Column("contract_event_id")
    private UUID contractEventId;

    @Column("contract_id")
    private UUID contractId;

    @Column("event_type")
    private EventTypeEnum eventType;

    @Column("event_date")
    private LocalDateTime eventDate;

    @Column("event_description")
    private String eventDescription;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
