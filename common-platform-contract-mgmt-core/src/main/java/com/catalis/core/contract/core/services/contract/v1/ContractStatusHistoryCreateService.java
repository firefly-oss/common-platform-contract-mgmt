package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractStatusHistoryMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractStatusHistoryCreateService {

    @Autowired
    private ContractStatusHistoryRepository repository;
    
    @Autowired
    private ContractStatusHistoryMapper mapper;

    /**
     * Creates a new contract status history entry based on the provided data.
     *
     * @param contractStatusHistoryDTO the data transfer object containing the details of the contract status history to be created
     * @return a Mono emitting the created contract status history data transfer object
     */
    public Mono<ContractStatusHistoryDTO> createContractStatusHistory(ContractStatusHistoryDTO contractStatusHistoryDTO) {
        return Mono.just(contractStatusHistoryDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(savedEntity -> {
                    contractStatusHistoryDTO.setContractStatusHistoryId(savedEntity.getContractStatusHistoryId());
                    return mapper.toDto(savedEntity);
                });
    }

}
