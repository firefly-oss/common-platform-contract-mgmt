package com.catalis.core.contract.core.mappers.contract.v1;

import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractStatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractStatusHistoryMapper {
    ContractStatusHistoryDTO toDto(ContractStatusHistory entity);
    ContractStatusHistory toEntity(ContractStatusHistoryDTO dto);
}
