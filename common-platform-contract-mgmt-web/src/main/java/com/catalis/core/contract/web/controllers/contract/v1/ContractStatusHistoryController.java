package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.*;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
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
@RequestMapping("/api/v1/contracts-status-history")
@Tag(name = "Contract Management API", description = "API for managing contracts, including creation, retrieval, update, deletion, and advanced querying options")
public class ContractStatusHistoryController {

    @Autowired
    private ContractStatusHistoryCreateService createService;

    @Autowired
    private ContractStatusHistoryGetService getService;

    @Autowired
    private ContractStatusHistoryUpdateService updateService;

    @Autowired
    private ContractStatusHistoryDeleteService deleteService;

    /**
     * Create a new contract status history entry.
     *
     * @param dto The DTO containing the data to create a new entry.
     * @return Mono of the created ContractStatusHistoryDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract status history entry",
            description = "Creates a contract status history entry for the provided data",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created contract status history entry",
                            content = @Content(schema = @Schema(implementation = ContractStatusHistoryDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractStatusHistoryDTO> createContractStatusHistory(@RequestBody ContractStatusHistoryDTO dto) {
        return createService.createContractStatusHistory(dto);
    }

    /**
     * Get a contract status history entry by ID.
     *
     * @param id The unique identifier of the entry.
     * @return Mono of the requested ContractStatusHistoryDTO.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a contract status history entry by ID",
            description = "Fetches the contract status history entry for the provided ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved contract status history entry",
                            content = @Content(schema = @Schema(implementation = ContractStatusHistoryDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract status history entry not found", content = @Content)
            }
    )
    public Mono<ContractStatusHistoryDTO> getContractStatusHistoryById(@PathVariable Long id) {
        return getService.getContractStatusHistory(id);
    }

    /**
     * Retrieve all status history entries for a contract with pagination.
     *
     * @param contractId        The ID of the contract whose history is being retrieved.
     * @param paginationRequest The pagination details (page, size, etc.).
     * @return Mono containing paginated results of contract status history entries.
     */
    @GetMapping("/contract/{contractId}")
    @Operation(
            summary = "Retrieve all status history entries for a contract",
            description = "Fetches all status history entries related to a contract, with support for pagination",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of contract status history entries",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No entries found for the specified contract", content = @Content)
            }
    )
    public Mono<PaginationResponse<ContractStatusHistoryDTO>> getStatusHistoryForContract(
            @PathVariable Long contractId, PaginationRequest paginationRequest) {
        return getService.findByContractIdOrderByStatusStartDateDesc(contractId, paginationRequest);
    }

    /**
     * Update an existing contract status history entry by ID.
     *
     * @param id  The unique identifier of the entry to be updated.
     * @param dto The updated details of the entry.
     * @return Mono of the updated ContractStatusHistoryDTO.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract status history entry",
            description = "Updates the details of an existing contract status history entry",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the contract status history entry",
                            content = @Content(schema = @Schema(implementation = ContractStatusHistoryDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract status history entry not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractStatusHistoryDTO> updateContractStatusHistory(@PathVariable Long id, @RequestBody ContractStatusHistoryDTO dto) {
        return updateService.updateContractStatusHistory(id, dto);
    }

    /**
     * Delete a contract status history entry by ID.
     *
     * @param id The unique identifier of the entry to delete.
     * @return Mono signaling the deletion completion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract status history entry by ID",
            description = "Deletes an existing contract status history entry identified by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the contract status history entry", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contract status history entry not found", content = @Content)
            }
    )
    public Mono<Void> deleteContractStatusHistory(@PathVariable Long id) {
        return deleteService.deleteContractStatusHistory(id);
    }
}