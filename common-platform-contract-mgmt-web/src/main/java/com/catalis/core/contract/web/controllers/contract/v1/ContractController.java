package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.ContractCreateService;
import com.catalis.core.contract.core.services.contract.v1.ContractDeleteService;
import com.catalis.core.contract.core.services.contract.v1.ContractGetService;
import com.catalis.core.contract.core.services.contract.v1.ContractUpdateService;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
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
@RequestMapping("/api/v1/contracts")
@Tag(name = "Contract Management API", description = "API for managing contracts, including creation, retrieval, update, deletion, and advanced querying options")
public class ContractController {

    @Autowired
    private ContractCreateService createService;
    
    @Autowired
    private ContractUpdateService updateService;
    
    @Autowired
    private ContractGetService getService;
    
    @Autowired
    private ContractDeleteService deleteService;

    /**
     * Create a new Contract.
     *
     * @param contractDTO the data to create the contract
     * @return Mono emitting the created contract
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract",
            description = "Creates a new contract based on the provided data",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Contract created successfully",
                            content = @Content(schema = @Schema(implementation = ContractDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) {
        return createService.createContract(contractDTO);
    }

    /**
     * Update an existing Contract.
     *
     * @param id          the ID of the contract to update
     * @param contractDTO the updated contract data
     * @return Mono emitting the updated contract
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract",
            description = "Updates an existing contract with the given ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract updated successfully",
                            content = @Content(schema = @Schema(implementation = ContractDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractDTO> updateContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        return updateService.updateContract(id, contractDTO);
    }

    /**
     * Get a Contract by ID.
     *
     * @param id the ID of the contract
     * @return Mono emitting the contract data
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a contract by ID",
            description = "Retrieve a contract's details using its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract retrieved successfully",
                            content = @Content(schema = @Schema(implementation = ContractDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)
            }
    )
    public Mono<ContractDTO> getContractById(@PathVariable Long id) {
        return getService.getContract(id);
    }

    /**
     * Get a Contract by Contract Number.
     *
     * @param contractNumber the contract number
     * @return Mono emitting the contract data
     */
    @GetMapping("/number/{contractNumber}")
    @Operation(
            summary = "Get a contract by contract number",
            description = "Retrieve a contract's details using its contract number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contract retrieved successfully",
                            content = @Content(schema = @Schema(implementation = ContractDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)
            }
    )
    public Mono<ContractDTO> getContractByContractNumber(@PathVariable String contractNumber) {
        return getService.getContractByContractNumber(contractNumber);
    }

    /**
     * Get Contracts by Product ID with Pagination.
     *
     * @param productId          the product ID
     * @param paginationRequest  the pagination request
     * @return Mono emitting the paginated response of contracts
     */
    @GetMapping("/product/{productId}")
    @Operation(
            summary = "Get contracts by product ID",
            description = "Retrieve a paginated list of contracts associated with a given product ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contracts retrieved successfully",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No contracts found", content = @Content)
            }
    )
    public Mono<PaginationResponse<ContractDTO>> findByProductId(
            @PathVariable Long productId,
            PaginationRequest paginationRequest) {
        return getService.findByProductId(productId, paginationRequest);
    }

    /**
     * Delete a Contract by ID.
     *
     * @param id the ID of the contract to delete
     * @return Mono signaling when deletion is complete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract by ID",
            description = "Deletes an existing contract using its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Contract deleted successfully",
                            content = @Content
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Deletion failed", content = @Content)
            }
    )
    public Mono<Void> deleteContract(@PathVariable Long id) {
        return deleteService.deleteContract(id);
    }
}