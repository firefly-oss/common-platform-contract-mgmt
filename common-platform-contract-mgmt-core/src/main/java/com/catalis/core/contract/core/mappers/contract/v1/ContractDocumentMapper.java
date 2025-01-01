package com.catalis.core.contract.core.mappers.contract.v1;

import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractDocumentMapper {
    ContractDocumentDTO toDto(ContractDocument entity);
    ContractDocument toEntity(ContractDocumentDTO dto);
}
