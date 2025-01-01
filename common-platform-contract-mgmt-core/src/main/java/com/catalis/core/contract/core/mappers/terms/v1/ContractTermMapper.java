package com.catalis.core.contract.core.mappers.terms.v1;

import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.catalis.core.contract.models.entities.terms.v1.ContractTerm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractTermMapper {
    ContractTermDTO toDto(ContractTerm entity);
    ContractTerm toEntity(ContractTermDTO dto);
}
