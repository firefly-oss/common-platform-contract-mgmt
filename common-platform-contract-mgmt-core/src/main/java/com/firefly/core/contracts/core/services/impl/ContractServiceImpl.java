package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractMapper;
import com.firefly.core.contracts.core.services.ContractService;
import com.firefly.core.contracts.interfaces.dtos.ContractDTO;
import com.firefly.core.contracts.models.entities.Contract;
import com.firefly.core.contracts.models.repositories.ContractRepository;
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
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractDTO> createContract(ContractDTO contractDTO) {
        return Mono.just(contractDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractDTO> updateContract(Long contractId, ContractDTO contractDTO) {
        return repository.findById(contractId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract not found with ID: " + contractId)))
                .flatMap(existingContract -> {
                    Contract updatedContract = mapper.toEntity(contractDTO);
                    updatedContract.setContractId(contractId);
                    return repository.save(updatedContract);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContract(Long contractId) {
        return repository.findById(contractId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract not found with ID: " + contractId)))
                .flatMap(contract -> repository.deleteById(contractId));
    }

    @Override
    public Mono<ContractDTO> getContractById(Long contractId) {
        return repository.findById(contractId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract not found with ID: " + contractId)))
                .map(mapper::toDTO);
    }
}
