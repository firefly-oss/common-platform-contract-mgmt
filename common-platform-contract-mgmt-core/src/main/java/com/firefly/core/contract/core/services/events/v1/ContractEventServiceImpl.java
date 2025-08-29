package com.firefly.core.contract.core.services.events.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.contract.core.mappers.events.v1.ContractEventMapper;
import com.firefly.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.firefly.core.contract.models.entities.events.v1.ContractEvent;
import com.firefly.core.contract.models.repositories.events.v1.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractEventServiceImpl implements ContractEventService {

    @Autowired
    private ContractEventRepository repository;

    @Autowired
    private ContractEventMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractEventDTO>> getAllEvents(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }

    @Override
    public Mono<ContractEventDTO> createEvent(Long contractId, ContractEventDTO dto) {
        dto.setContractId(contractId);
        ContractEvent entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractEventDTO> getEvent(Long contractId, Long eventId) {
        return repository.findById(eventId)
                .filter(event -> event.getContractId().equals(contractId))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractEventDTO> updateEvent(Long contractId, Long eventId, ContractEventDTO dto) {
        return repository.findById(eventId)
                .filter(event -> event.getContractId().equals(contractId))
                .flatMap(existingEvent -> {
                    ContractEvent updatedEvent = mapper.toEntity(dto);
                    updatedEvent.setContractEventId(eventId);
                    updatedEvent.setContractId(contractId);
                    updatedEvent.setDateCreated(existingEvent.getDateCreated());
                    return repository.save(updatedEvent);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteEvent(Long contractId, Long eventId) {
        return repository.findById(eventId)
                .filter(event -> event.getContractId().equals(contractId))
                .flatMap(repository::delete);
    }
}