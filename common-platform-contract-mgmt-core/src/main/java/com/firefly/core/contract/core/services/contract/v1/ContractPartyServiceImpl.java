package com.firefly.core.contract.core.services.contract.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.contract.core.mappers.contract.v1.ContractPartyMapper;
import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import com.firefly.core.contract.models.entities.contract.v1.ContractParty;
import com.firefly.core.contract.models.repositories.contract.v1.ContractPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractPartyServiceImpl implements ContractPartyService {

    @Autowired
    private ContractPartyRepository repository;

    @Autowired
    private ContractPartyMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractPartyDTO>> getAllParties(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }

    @Override
    public Mono<ContractPartyDTO> createPartyLink(Long contractId, ContractPartyDTO dto) {
        dto.setContractId(contractId);
        ContractParty entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractPartyDTO> getParty(Long contractId, Long partyId) {
        return repository.findByContractId(contractId)
                .filter(contractParty -> contractParty.getPartyId().equals(partyId))
                .singleOrEmpty()
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractPartyDTO> updateParty(Long contractId, Long partyId, ContractPartyDTO dto) {
        return repository.findByContractId(contractId)
                .filter(contractParty -> contractParty.getPartyId().equals(partyId))
                .singleOrEmpty()
                .flatMap(existingEntity -> {
                    ContractParty updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setContractPartyId(existingEntity.getContractPartyId());
                    updatedEntity.setContractId(contractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteParty(Long contractId, Long partyId) {
        return repository.findByContractId(contractId)
                .filter(contractParty -> contractParty.getPartyId().equals(partyId))
                .singleOrEmpty()
                .flatMap(repository::delete);
    }
}
