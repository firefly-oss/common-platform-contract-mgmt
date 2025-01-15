package com.catalis.core.contract.core.mappers.contract.v1;

import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractStatusHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:45:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContractStatusHistoryMapperImpl implements ContractStatusHistoryMapper {

    @Override
    public ContractStatusHistoryDTO toDto(ContractStatusHistory entity) {
        if ( entity == null ) {
            return null;
        }

        ContractStatusHistoryDTO.ContractStatusHistoryDTOBuilder<?, ?> contractStatusHistoryDTO = ContractStatusHistoryDTO.builder();

        contractStatusHistoryDTO.dateCreated( entity.getDateCreated() );
        contractStatusHistoryDTO.dateUpdated( entity.getDateUpdated() );
        contractStatusHistoryDTO.contractStatusHistoryId( entity.getContractStatusHistoryId() );
        contractStatusHistoryDTO.contractId( entity.getContractId() );
        contractStatusHistoryDTO.statusCode( entity.getStatusCode() );
        contractStatusHistoryDTO.statusStartDate( entity.getStatusStartDate() );
        contractStatusHistoryDTO.statusEndDate( entity.getStatusEndDate() );

        return contractStatusHistoryDTO.build();
    }

    @Override
    public ContractStatusHistory toEntity(ContractStatusHistoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ContractStatusHistory contractStatusHistory = new ContractStatusHistory();

        contractStatusHistory.setDateCreated( dto.getDateCreated() );
        contractStatusHistory.setDateUpdated( dto.getDateUpdated() );
        contractStatusHistory.setContractStatusHistoryId( dto.getContractStatusHistoryId() );
        contractStatusHistory.setContractId( dto.getContractId() );
        contractStatusHistory.setStatusCode( dto.getStatusCode() );
        contractStatusHistory.setStatusStartDate( dto.getStatusStartDate() );
        contractStatusHistory.setStatusEndDate( dto.getStatusEndDate() );

        return contractStatusHistory;
    }
}
