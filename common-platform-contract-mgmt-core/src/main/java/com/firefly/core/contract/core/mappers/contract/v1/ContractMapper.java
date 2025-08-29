package com.firefly.core.contract.core.mappers.contract.v1;

import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import com.firefly.core.contract.models.entities.contract.v1.Contract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractDTO toDto(Contract entity);
    Contract toEntity(ContractDTO dto);
}
