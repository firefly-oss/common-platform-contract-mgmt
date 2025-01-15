package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.mappers.contract.v1.ContractMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import com.catalis.core.contract.models.entities.contract.v1.Contract;
import com.catalis.core.contract.models.repositories.contract.v1.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository repository;

    @Autowired
    private ContractMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractDTO>> filterContracts(FilterRequest<ContractDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        Contract.class,
                        mapper::toDto
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractDTO> createContract(ContractDTO dto) {
        Contract contract = mapper.toEntity(dto);
        return repository.save(contract)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractDTO> getContract(Long contractId) {
        return repository.findById(contractId)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractDTO> updateContract(Long contractId, ContractDTO dto) {
        return repository.findById(contractId)
                .flatMap(existingContract -> {
                    Contract updatedContract = mapper.toEntity(dto);
                    updatedContract.setContractId(existingContract.getContractId());
                    return repository.save(updatedContract);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteContract(Long contractId) {
        return repository.deleteById(contractId);
    }
}