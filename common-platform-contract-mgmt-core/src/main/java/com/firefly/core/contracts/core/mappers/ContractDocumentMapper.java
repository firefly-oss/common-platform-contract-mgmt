package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractDocumentDTO;
import com.firefly.core.contracts.models.entities.ContractDocument;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractDocument entity and ContractDocumentDTO
 */
@Mapper(componentModel = "spring")
public interface ContractDocumentMapper {

    /**
     * Convert ContractDocument entity to ContractDocumentDTO
     *
     * @param contractDocument the ContractDocument entity
     * @return the ContractDocumentDTO
     */
    ContractDocumentDTO toDTO(ContractDocument contractDocument);

    /**
     * Convert ContractDocumentDTO to ContractDocument entity
     *
     * @param contractDocumentDTO the ContractDocumentDTO
     * @return the ContractDocument entity
     */
    ContractDocument toEntity(ContractDocumentDTO contractDocumentDTO);
}
