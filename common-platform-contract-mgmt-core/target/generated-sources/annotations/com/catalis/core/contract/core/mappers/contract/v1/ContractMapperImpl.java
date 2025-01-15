package com.catalis.core.contract.core.mappers.contract.v1;

import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import com.catalis.core.contract.models.entities.contract.v1.Contract;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:45:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContractMapperImpl implements ContractMapper {

    @Override
    public ContractDTO toDto(Contract entity) {
        if ( entity == null ) {
            return null;
        }

        ContractDTO.ContractDTOBuilder<?, ?> contractDTO = ContractDTO.builder();

        contractDTO.dateCreated( entity.getDateCreated() );
        contractDTO.dateUpdated( entity.getDateUpdated() );
        contractDTO.contractId( entity.getContractId() );
        contractDTO.contractNumber( entity.getContractNumber() );
        contractDTO.productId( entity.getProductId() );
        contractDTO.contractType( entity.getContractType() );
        contractDTO.contractStatus( entity.getContractStatus() );
        contractDTO.startDate( entity.getStartDate() );
        contractDTO.endDate( entity.getEndDate() );
        contractDTO.documentManagerRef( entity.getDocumentManagerRef() );

        return contractDTO.build();
    }

    @Override
    public Contract toEntity(ContractDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Contract contract = new Contract();

        contract.setDateCreated( dto.getDateCreated() );
        contract.setDateUpdated( dto.getDateUpdated() );
        contract.setContractId( dto.getContractId() );
        contract.setContractNumber( dto.getContractNumber() );
        contract.setProductId( dto.getProductId() );
        contract.setContractType( dto.getContractType() );
        contract.setContractStatus( dto.getContractStatus() );
        contract.setStartDate( dto.getStartDate() );
        contract.setEndDate( dto.getEndDate() );
        contract.setDocumentManagerRef( dto.getDocumentManagerRef() );

        return contract;
    }
}
