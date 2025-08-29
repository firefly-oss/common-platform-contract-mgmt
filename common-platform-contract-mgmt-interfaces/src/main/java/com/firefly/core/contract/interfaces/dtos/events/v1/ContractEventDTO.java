package com.firefly.core.contract.interfaces.dtos.events.v1;

import com.firefly.annotations.ValidDate;
import com.firefly.core.contract.interfaces.dtos.BaseDTO;
import com.firefly.core.contract.interfaces.enums.events.v1.EventTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContractEventDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractEventId;

    @FilterableId
    private Long contractId;

    private EventTypeEnum eventType;

    @ValidDate
    private LocalDateTime eventDate;

    private String eventDescription;
    private String documentManagerRef;

}
