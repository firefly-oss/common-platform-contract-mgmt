package com.firefly.core.contract.core.mappers.contract.v1;

import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.firefly.core.contract.models.entities.contract.v1.ContractDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractDocumentMapper {
    ContractDocumentDTO toDto(ContractDocument entity);
    ContractDocument toEntity(ContractDocumentDTO dto);
}
