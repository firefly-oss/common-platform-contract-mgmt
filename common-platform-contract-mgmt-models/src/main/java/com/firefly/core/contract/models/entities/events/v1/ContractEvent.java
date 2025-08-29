package com.firefly.core.contract.models.entities.events.v1;

import com.firefly.core.contract.interfaces.enums.events.v1.EventTypeEnum;
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
@Table("contract_event")
public class ContractEvent extends BaseEntity {
    @Id
    @Column("contract_event_id")
    private Long contractEventId;

    @Column("contract_id")
    private Long contractId;

    @Column("event_type")
    private EventTypeEnum eventType;

    @Column("event_date")
    private LocalDateTime eventDate;

    @Column("event_description")
    private String eventDescription;

    @Column("document_manager_ref")
    private String documentManagerRef;
}
