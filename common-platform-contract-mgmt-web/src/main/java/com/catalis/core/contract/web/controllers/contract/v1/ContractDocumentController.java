package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.ContractDocumentCreateService;
import com.catalis.core.contract.core.services.contract.v1.ContractDocumentDeleteService;
import com.catalis.core.contract.core.services.contract.v1.ContractDocumentGetService;
import com.catalis.core.contract.core.services.contract.v1.ContractDocumentUpdateService;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/contracts-documents")
@Tag(name = "Contract Management API", description = "API for managing contracts, including creation, retrieval, update, deletion, and advanced querying options")
public class ContractDocumentController {

    @Autowired
    private ContractDocumentCreateService createService;

    @Autowired
    private ContractDocumentGetService getService;

    @Autowired
    private ContractDocumentUpdateService updateService;

    @Autowired
    private ContractDocumentDeleteService deleteService;

    /**
     * Create a new contract document.
     *
     * @param contractDocumentDTO The DTO containing the data to create a new contract document.
     * @return Mono of the created ContractDocumentDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract document",
            description = "Creates a new contract document with the provided details",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Contract document created successfully",
                            content = @Content(schema = @Schema(implementation = ContractDocumentDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractDocumentDTO> createContractDocument(@RequestBody ContractDocumentDTO contractDocumentDTO) {
        return createService.createContractDocument(contractDocumentDTO);
    }

    /**
     * Get a contract document by its ID.
     *
     * @param id The unique identifier of the contract document.
     * @return Mono of the requested ContractDocumentDTO.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a contract document by ID",
            description = "Retrieves a contract document by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract document retrieved successfully",
                            content = @Content(schema = @Schema(implementation = ContractDocumentDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract document not found", content = @Content)
            }
    )
    public Mono<ContractDocumentDTO> getContractDocumentById(@PathVariable Long id) {
        return getService.getContractDocument(id);
    }

    /**
     * Get contract documents by contract ID with pagination.
     *
     * @param contractId The unique identifier of the contract.
     * @param paginationRequest The pagination details (page, size).
     * @return Mono of a paginated response with a list of ContractDocumentDTOs.
     */
    @GetMapping("/contract/{contractId}")
    @Operation(
            summary = "Retrieve contract documents by contract ID",
            description = "Retrieves a paginated list of contract documents associated with a specified contract ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract documents retrieved successfully",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No contract documents found", content = @Content)
            }
    )
    public Mono<PaginationResponse<ContractDocumentDTO>> getDocumentsByContractId(
            @PathVariable Long contractId,
            PaginationRequest paginationRequest) {
        return getService.findByContractId(contractId, paginationRequest);
    }

    /**
     * Update a contract document by its ID.
     *
     * @param id The unique identifier of the contract document to be updated.
     * @param contractDocumentDTO The updated details for the contract document.
     * @return Mono of the updated ContractDocumentDTO.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract document",
            description = "Updates an existing contract document identified by its ID with the provided data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract document updated successfully",
                            content = @Content(schema = @Schema(implementation = ContractDocumentDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract document not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractDocumentDTO> updateContractDocument(@PathVariable Long id,
                                                            @RequestBody ContractDocumentDTO contractDocumentDTO) {
        return updateService.updateContractDocument(id, contractDocumentDTO);
    }

    /**
     * Delete a contract document by its ID.
     *
     * @param id The unique identifier of the contract document to be deleted.
     * @return Mono signaling deletion completion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract document by ID",
            description = "Deletes a contract document identified by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Contract document deleted successfully", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contract document not found", content = @Content)
            }
    )
    public Mono<Void> deleteContractDocument(@PathVariable Long id) {
        return deleteService.deleteContractDocument(id);
    }
}