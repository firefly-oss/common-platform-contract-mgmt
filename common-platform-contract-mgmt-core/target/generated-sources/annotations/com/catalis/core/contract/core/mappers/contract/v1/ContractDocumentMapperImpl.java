package com.catalis.core.contract.core.mappers.contract.v1;

import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:45:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContractDocumentMapperImpl implements ContractDocumentMapper {

    @Override
    public ContractDocumentDTO toDto(ContractDocument entity) {
        if ( entity == null ) {
            return null;
        }

        ContractDocumentDTO.ContractDocumentDTOBuilder<?, ?> contractDocumentDTO = ContractDocumentDTO.builder();

        contractDocumentDTO.dateCreated( entity.getDateCreated() );
        contractDocumentDTO.dateUpdated( entity.getDateUpdated() );
        contractDocumentDTO.contractDocumentId( entity.getContractDocumentId() );
        contractDocumentDTO.contractId( entity.getContractId() );
        contractDocumentDTO.documentType( entity.getDocumentType() );
        contractDocumentDTO.documentManagerRef( entity.getDocumentManagerRef() );
        contractDocumentDTO.dateAdded( entity.getDateAdded() );

        return contractDocumentDTO.build();
    }

    @Override
    public ContractDocument toEntity(ContractDocumentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ContractDocument contractDocument = new ContractDocument();

        contractDocument.setDateCreated( dto.getDateCreated() );
        contractDocument.setDateUpdated( dto.getDateUpdated() );
        contractDocument.setContractDocumentId( dto.getContractDocumentId() );
        contractDocument.setContractId( dto.getContractId() );
        contractDocument.setDocumentType( dto.getDocumentType() );
        contractDocument.setDocumentManagerRef( dto.getDocumentManagerRef() );
        contractDocument.setDateAdded( dto.getDateAdded() );

        return contractDocument;
    }
}
