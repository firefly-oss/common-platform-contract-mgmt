package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractCreateService {
    
    @Autowired
    private ContractRepository repository;
    
    @Autowired
    private ContractMapper mapper;

    /**
     * Creates a new contract based on the provided contract data.
     *
     * @param request the contract data transfer object containing the details of the contract to be created
     * @return a Mono emitting the created contract data transfer object
     */
    public Mono<ContractDTO> createContract(ContractDTO request) {
        return Mono.just(request)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(savedEntity -> {
                    request.setContractId(savedEntity.getContractId());
                    return mapper.toDto(savedEntity);
                });
    }
    
}