package com.firefly.core.contract.core.mappers.terms.v1;

import com.firefly.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.firefly.core.contract.models.entities.terms.v1.ContractTerm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractTermMapper {
    ContractTermDTO toDto(ContractTerm entity);
    ContractTerm toEntity(ContractTermDTO dto);
}
