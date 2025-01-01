package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.contract.v1.ContractDocumentMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractDocumentGetService {

    @Autowired
    private ContractDocumentRepository repository;

    @Autowired
    private ContractDocumentMapper mapper;

    /**
     * Retrieves a contract document by its unique identifier.
     *
     * @param contractDocumentId the unique identifier of the contract document to be retrieved
     * @return a Mono emitting the ContractDocumentDTO object if found, or an error if the document does not exist
     */
    public Mono<ContractDocumentDTO> getContractDocument(Long contractDocumentId) {
        return repository.findById(contractDocumentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract document with ID " + contractDocumentId + " not found")))
                .map(mapper::toDto);
    }

    /**
     * Retrieves a paginated list of contract documents filtered by the given contract ID.
     *
     * @param contractId the unique identifier of the contract whose documents are to be retrieved
     * @param paginationRequest an object containing pagination parameters like page number and size
     * @return a Mono emitting a paginated response containing the list of contract document DTOs
     */
    public Mono<PaginationResponse<ContractDocumentDTO>> findByContractId(Long contractId,
                                                                          PaginationRequest paginationRequest){

        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );

    }

}
