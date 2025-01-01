package com.catalis.core.contract.core.services.events.v1;

import com.catalis.core.contract.core.mappers.events.v1.ContractEventMapper;
import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.catalis.core.contract.models.repositories.events.v1.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractEventCreateService {

    @Autowired
    private ContractEventRepository repository;

    @Autowired
    private ContractEventMapper mapper;

    /**
     * Creates and saves a new ContractEvent in the database.
     *
     * @param contractEvent The data transfer object (DTO) containing the details of the contract event to be created.
     * @return A {@link Mono} emitting the saved ContractEventDTO after the operation completes.
     */
    public Mono<ContractEventDTO> createContractEvent(ContractEventDTO contractEvent) {
        return Mono.just(contractEvent)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(entity -> {
                    ContractEventDTO dto = mapper.toDto(entity);
                    dto.setContractEventId(entity.getContractEventId());
                    return dto;
                });
    }

}
