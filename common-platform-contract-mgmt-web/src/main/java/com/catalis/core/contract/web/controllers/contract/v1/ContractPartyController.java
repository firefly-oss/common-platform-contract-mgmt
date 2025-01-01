package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.ContractPartyCreateService;
import com.catalis.core.contract.core.services.contract.v1.ContractPartyDeleteService;
import com.catalis.core.contract.core.services.contract.v1.ContractPartyGetService;
import com.catalis.core.contract.core.services.contract.v1.ContractPartyUpdateService;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
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
@RequestMapping("/api/v1/contracts-parties")
@Tag(name = "Contract Management API", description = "API for managing contracts, including creation, retrieval, update, deletion, and advanced querying options")
public class ContractPartyController {

    @Autowired
    private ContractPartyCreateService createService;

    @Autowired
    private ContractPartyGetService getService;

    @Autowired
    private ContractPartyUpdateService updateService;

    @Autowired
    private ContractPartyDeleteService deleteService;

    /**
     * Create a new contract party.
     *
     * @param contractPartyDTO The DTO containing the data to create a new contract party.
     * @return Mono of the created ContractPartyDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract party",
            description = "Creates a contract party with the provided information",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Contract party created successfully",
                            content = @Content(schema = @Schema(implementation = ContractPartyDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractPartyDTO> createContractParty(@RequestBody ContractPartyDTO contractPartyDTO) {
        return createService.createContractParty(contractPartyDTO);
    }

    /**
     * Get a contract party by its ID.
     *
     * @param id The unique identifier of the contract party.
     * @return Mono of the requested ContractPartyDTO.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a contract party by ID",
            description = "Fetches the contract party for the provided ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract party retrieved successfully",
                            content = @Content(schema = @Schema(implementation = ContractPartyDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract party not found", content = @Content)
            }
    )
    public Mono<ContractPartyDTO> getContractPartyById(@PathVariable Long id) {
        return getService.getContractParty(id);
    }

    /**
     * Retrieve contract parties by contract ID with pagination.
     *
     * @param contractId The unique ID of the contract.
     * @param paginationRequest The pagination request containing page size, number, etc.
     * @return Mono of a paginated response containing a list of ContractPartyDTOs.
     */
    @GetMapping("/contract/{contractId}")
    @Operation(
            summary = "Retrieve contract parties by contract ID",
            description = "Fetches paginated contract parties for the specified contract ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract parties retrieved successfully",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No contract parties found for the given contract ID", content = @Content)
            }
    )
    public Mono<PaginationResponse<ContractPartyDTO>> getContractPartiesByContractId(
            @PathVariable Long contractId,
            PaginationRequest paginationRequest) {
        return getService.findByContractId(contractId, paginationRequest);
    }

    /**
     * Update a contract party by its ID.
     *
     * @param id The unique identifier of the contract party to be updated.
     * @param contractPartyDTO The updated details for the contract party.
     * @return Mono of the updated ContractPartyDTO.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract party",
            description = "Updates the details of an existing contract party identified by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract party updated successfully",
                            content = @Content(schema = @Schema(implementation = ContractPartyDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract party not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractPartyDTO> updateContractParty(@PathVariable Long id, @RequestBody ContractPartyDTO contractPartyDTO) {
        return updateService.updateContractParty(id, contractPartyDTO);
    }

    /**
     * Delete a contract party by its ID.
     *
     * @param id The unique identifier of the contract party to delete.
     * @return Mono signaling completion of deletion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract party by ID",
            description = "Deletes an existing contract party identified by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Contract party deleted successfully",
                            content = @Content
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract party not found", content = @Content)
            }
    )
    public Mono<Void> deleteContractParty(@PathVariable Long id) {
        return deleteService.deleteContractParty(id);
    }
}