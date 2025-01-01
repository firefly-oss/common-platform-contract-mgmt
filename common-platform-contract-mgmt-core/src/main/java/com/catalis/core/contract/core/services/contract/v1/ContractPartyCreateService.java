package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractPartyMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractPartyCreateService {

    @Autowired
    private ContractPartyRepository repository;

    @Autowired
    private ContractPartyMapper mapper;

    /**
     * Creates a new contract party based on the provided contract party data.
     *
     * @param request the contract party data transfer object containing the details of the contract party to be created
     * @return a Mono emitting the created contract party data transfer object, including the generated ID
     */
    public Mono<ContractPartyDTO> createContractParty(ContractPartyDTO request) {
        return Mono.just(request)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(savedEntity -> {
                    ContractPartyDTO dto = mapper.toDto(savedEntity);
                    dto.setContractPartyId(savedEntity.getContractPartyId());
                    return dto;
                });
    }

}
