package com.firefly.core.contract.core.mappers.contract.v1;

import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import com.firefly.core.contract.models.entities.contract.v1.ContractParty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractPartyMapper {
    ContractPartyDTO toDto(ContractParty entity);
    ContractParty toEntity(ContractPartyDTO dto);
}
