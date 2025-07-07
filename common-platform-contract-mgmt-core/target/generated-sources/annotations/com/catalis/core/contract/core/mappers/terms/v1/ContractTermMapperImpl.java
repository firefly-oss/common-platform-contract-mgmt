package com.catalis.core.contract.core.mappers.terms.v1;

import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.catalis.core.contract.models.entities.terms.v1.ContractTerm;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-07T17:44:01+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ContractTermMapperImpl implements ContractTermMapper {

    @Override
    public ContractTermDTO toDto(ContractTerm entity) {
        if ( entity == null ) {
            return null;
        }

        ContractTermDTO.ContractTermDTOBuilder<?, ?> contractTermDTO = ContractTermDTO.builder();

        contractTermDTO.dateCreated( entity.getDateCreated() );
        contractTermDTO.dateUpdated( entity.getDateUpdated() );
        contractTermDTO.contractTermId( entity.getContractTermId() );
        contractTermDTO.contractId( entity.getContractId() );
        contractTermDTO.termType( entity.getTermType() );
        contractTermDTO.termDescription( entity.getTermDescription() );
        contractTermDTO.numericValue( entity.getNumericValue() );
        contractTermDTO.valueUnit( entity.getValueUnit() );
        contractTermDTO.effectiveDate( entity.getEffectiveDate() );
        contractTermDTO.expirationDate( entity.getExpirationDate() );

        return contractTermDTO.build();
    }

    @Override
    public ContractTerm toEntity(ContractTermDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ContractTerm contractTerm = new ContractTerm();

        contractTerm.setDateCreated( dto.getDateCreated() );
        contractTerm.setDateUpdated( dto.getDateUpdated() );
        contractTerm.setContractTermId( dto.getContractTermId() );
        contractTerm.setContractId( dto.getContractId() );
        contractTerm.setTermType( dto.getTermType() );
        contractTerm.setTermDescription( dto.getTermDescription() );
        contractTerm.setNumericValue( dto.getNumericValue() );
        contractTerm.setValueUnit( dto.getValueUnit() );
        contractTerm.setEffectiveDate( dto.getEffectiveDate() );
        contractTerm.setExpirationDate( dto.getExpirationDate() );

        return contractTerm;
    }
}
