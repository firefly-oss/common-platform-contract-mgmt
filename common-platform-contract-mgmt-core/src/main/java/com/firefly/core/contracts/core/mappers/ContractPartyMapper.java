package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractPartyDTO;
import com.firefly.core.contracts.models.entities.ContractParty;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractParty entity and ContractPartyDTO
 */
@Mapper(componentModel = "spring")
public interface ContractPartyMapper {

    /**
     * Convert ContractParty entity to ContractPartyDTO
     *
     * @param contractParty the ContractParty entity
     * @return the ContractPartyDTO
     */
    ContractPartyDTO toDTO(ContractParty contractParty);

    /**
     * Convert ContractPartyDTO to ContractParty entity
     *
     * @param contractPartyDTO the ContractPartyDTO
     * @return the ContractParty entity
     */
    ContractParty toEntity(ContractPartyDTO contractPartyDTO);
}
