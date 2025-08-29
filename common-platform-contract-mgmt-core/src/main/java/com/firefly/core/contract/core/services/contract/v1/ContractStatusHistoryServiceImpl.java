package com.firefly.core.contract.core.services.contract.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.contract.core.mappers.contract.v1.ContractStatusHistoryMapper;
import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import com.firefly.core.contract.models.entities.contract.v1.ContractStatusHistory;
import com.firefly.core.contract.models.repositories.contract.v1.ContractStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractStatusHistoryServiceImpl implements ContractStatusHistoryService {

    @Autowired
    private ContractStatusHistoryRepository repository;

    @Autowired
    private ContractStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractStatusHistoryDTO>> getAllStatuses(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }

    @Override
    public Mono<ContractStatusHistoryDTO> createStatusHistory(Long contractId, ContractStatusHistoryDTO dto) {
        ContractStatusHistory entity = mapper.toEntity(dto);
        entity.setContractId(contractId);
        return repository.save(entity)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractStatusHistoryDTO> getStatusHistory(Long contractId, Long historyId) {
        return repository.findById(historyId)
                .filter(history -> history.getContractId().equals(contractId))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractStatusHistoryDTO> updateStatusHistory(Long contractId, Long historyId, ContractStatusHistoryDTO dto) {
        return repository.findById(historyId)
                .filter(existingHistory -> existingHistory.getContractId().equals(contractId))
                .flatMap(existingHistory -> {
                    ContractStatusHistory updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setContractStatusHistoryId(existingHistory.getContractStatusHistoryId());
                    updatedEntity.setContractId(contractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteStatusHistory(Long contractId, Long historyId) {
        return repository.findById(historyId)
                .filter(history -> history.getContractId().equals(contractId))
                .flatMap(repository::delete);
    }
}
