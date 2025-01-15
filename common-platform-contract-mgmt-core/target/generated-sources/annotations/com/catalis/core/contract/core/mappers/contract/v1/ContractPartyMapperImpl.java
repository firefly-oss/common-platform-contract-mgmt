package com.catalis.core.contract.core.mappers.contract.v1;

import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractParty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:45:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContractPartyMapperImpl implements ContractPartyMapper {

    @Override
    public ContractPartyDTO toDto(ContractParty entity) {
        if ( entity == null ) {
            return null;
        }

        ContractPartyDTO.ContractPartyDTOBuilder<?, ?> contractPartyDTO = ContractPartyDTO.builder();

        contractPartyDTO.dateCreated( entity.getDateCreated() );
        contractPartyDTO.dateUpdated( entity.getDateUpdated() );
        contractPartyDTO.contractPartyId( entity.getContractPartyId() );
        contractPartyDTO.contractId( entity.getContractId() );
        contractPartyDTO.partyId( entity.getPartyId() );
        contractPartyDTO.roleInContract( entity.getRoleInContract() );
        contractPartyDTO.dateJoined( entity.getDateJoined() );
        contractPartyDTO.dateLeft( entity.getDateLeft() );
        contractPartyDTO.active( entity.getActive() );

        return contractPartyDTO.build();
    }

    @Override
    public ContractParty toEntity(ContractPartyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ContractParty contractParty = new ContractParty();

        contractParty.setDateCreated( dto.getDateCreated() );
        contractParty.setDateUpdated( dto.getDateUpdated() );
        contractParty.setContractPartyId( dto.getContractPartyId() );
        contractParty.setContractId( dto.getContractId() );
        contractParty.setPartyId( dto.getPartyId() );
        contractParty.setRoleInContract( dto.getRoleInContract() );
        contractParty.setDateJoined( dto.getDateJoined() );
        contractParty.setDateLeft( dto.getDateLeft() );
        contractParty.setActive( dto.getActive() );

        return contractParty;
    }
}
