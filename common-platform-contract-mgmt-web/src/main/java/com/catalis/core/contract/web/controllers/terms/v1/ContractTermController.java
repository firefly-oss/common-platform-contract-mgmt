package com.catalis.core.contract.web.controllers.terms.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.terms.v1.ContractTermCreateService;
import com.catalis.core.contract.core.services.terms.v1.ContractTermDeleteService;
import com.catalis.core.contract.core.services.terms.v1.ContractTermGetService;
import com.catalis.core.contract.core.services.terms.v1.ContractTermUpdateService;
import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/contracts-terms")
@Tag(name = "Contract Term API", description = "Endpoints to efficiently create, retrieve, update, and delete contract terms, including support for contract-specific querying and pagination.")
public class ContractTermController {

    @Autowired
    private ContractTermCreateService createService;

    @Autowired
    private ContractTermGetService getService;

    @Autowired
    private ContractTermUpdateService updateService;

    @Autowired
    private ContractTermDeleteService deleteService;

    /**
     * Create a new contract term.
     *
     * @param contractTermDTO The DTO containing the contract term's details.
     * @return ResponseEntity with created ContractTermDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract term",
            description = "Creates a new contract term based on the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the contract term.",
                            content = @Content(schema = @Schema(implementation = ContractTermDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
            }
    )
    public Mono<ResponseEntity<ContractTermDTO>> createContractTerm(@RequestBody ContractTermDTO contractTermDTO) {
        return createService.createContractTerm(contractTermDTO)
                .map(contractTerm -> ResponseEntity.status(HttpStatus.CREATED).body(contractTerm));
    }

    /**
     * Retrieve a contract term by its ID.
     *
     * @param id The unique identifier of the contract term.
     * @return ResponseEntity with the requested ContractTermDTO.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a contract term by ID",
            description = "Fetches the contract term associated with the specified ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the contract term.",
                            content = @Content(schema = @Schema(implementation = ContractTermDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract term not found", content = @Content)
            }
    )
    public Mono<ResponseEntity<ContractTermDTO>> getContractTermById(@PathVariable Long id) {
        return getService.getContractTerm(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Retrieve all terms related to a specific contract, with pagination.
     *
     * @param contractId        The unique identifier of the contract.
     * @param paginationRequest Contains pagination details like page number and size.
     * @return ResponseEntity containing a paginated list of ContractTermDTOs.
     */
    @GetMapping("/contract/{contractId}")
    @Operation(
            summary = "Retrieve all contract terms for a specific contract",
            description = "Fetches all terms associated with a specific contract ID with pagination support.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of contract terms.",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No terms found for the given contract", content = @Content)
            }
    )
    public Mono<ResponseEntity<PaginationResponse<ContractTermDTO>>> getTermsForContract(
            @PathVariable Long contractId, PaginationRequest paginationRequest) {
        return getService.findByContractId(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing contract term by its ID.
     *
     * @param id              The unique identifier of the contract term to update.
     * @param contractTermDTO The updated contract term details.
     * @return ResponseEntity with the updated ContractTermDTO.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract term",
            description = "Updates the details of an existing contract term by its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the contract term.",
                            content = @Content(schema = @Schema(implementation = ContractTermDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract term not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
            }
    )
    public Mono<ResponseEntity<ContractTermDTO>> updateContractTerm(
            @PathVariable Long id, @RequestBody ContractTermDTO contractTermDTO) {
        return updateService.updateContractTerm(id, contractTermDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Delete a contract term by its ID.
     *
     * @param id The unique identifier of the contract term to be deleted.
     * @return ResponseEntity signaling the deletion completion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract term",
            description = "Deletes the contract term associated with the specified ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the contract term.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contract term not found", content = @Content)
            }
    )
    public Mono<ResponseEntity<Void>> deleteContractTerm(@PathVariable Long id) {
        return deleteService.deleteContractTerm(id)
                .<ResponseEntity<Void>> map(deleted -> ResponseEntity.noContent().<Void>build())
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}