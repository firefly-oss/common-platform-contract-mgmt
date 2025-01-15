package com.catalis.core.contract.core.mappers.events.v1;

import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.catalis.core.contract.models.entities.events.v1.ContractEvent;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:45:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContractEventMapperImpl implements ContractEventMapper {

    @Override
    public ContractEventDTO toDto(ContractEvent entity) {
        if ( entity == null ) {
            return null;
        }

        ContractEventDTO.ContractEventDTOBuilder<?, ?> contractEventDTO = ContractEventDTO.builder();

        contractEventDTO.dateCreated( entity.getDateCreated() );
        contractEventDTO.dateUpdated( entity.getDateUpdated() );
        contractEventDTO.contractEventId( entity.getContractEventId() );
        contractEventDTO.contractId( entity.getContractId() );
        contractEventDTO.eventType( entity.getEventType() );
        contractEventDTO.eventDate( entity.getEventDate() );
        contractEventDTO.eventDescription( entity.getEventDescription() );
        contractEventDTO.documentManagerRef( entity.getDocumentManagerRef() );

        return contractEventDTO.build();
    }

    @Override
    public ContractEvent toEntity(ContractEventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ContractEvent contractEvent = new ContractEvent();

        contractEvent.setDateCreated( dto.getDateCreated() );
        contractEvent.setDateUpdated( dto.getDateUpdated() );
        contractEvent.setContractEventId( dto.getContractEventId() );
        contractEvent.setContractId( dto.getContractId() );
        contractEvent.setEventType( dto.getEventType() );
        contractEvent.setEventDate( dto.getEventDate() );
        contractEvent.setEventDescription( dto.getEventDescription() );
        contractEvent.setDocumentManagerRef( dto.getDocumentManagerRef() );

        return contractEvent;
    }
}
