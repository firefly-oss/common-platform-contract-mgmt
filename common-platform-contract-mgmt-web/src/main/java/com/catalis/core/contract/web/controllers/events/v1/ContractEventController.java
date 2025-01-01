package com.catalis.core.contract.web.controllers.events.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.events.v1.ContractEventCreateService;
import com.catalis.core.contract.core.services.events.v1.ContractEventDeleteService;
import com.catalis.core.contract.core.services.events.v1.ContractEventGetService;
import com.catalis.core.contract.core.services.events.v1.ContractEventUpdateService;
import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
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
@RequestMapping("/api/v1/contracts-events")
@Tag(name = "Contract Event API", description = "API for creating, retrieving, updating, and deleting contract events with support for pagination and detailed event management.")
public class ContractEventController {

    @Autowired
    private ContractEventCreateService createService;

    @Autowired
    private ContractEventGetService getService;

    @Autowired
    private ContractEventUpdateService updateService;

    @Autowired
    private ContractEventDeleteService deleteService;

    /**
     * Create a new contract event.
     *
     * @param contractEventDTO The DTO containing the data to create the contract event.
     * @return Mono of the created ContractEventDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract event",
            description = "Creates a new contract event based on the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the contract event.",
                            content = @Content(schema = @Schema(implementation = ContractEventDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractEventDTO> createContractEvent(@RequestBody ContractEventDTO contractEventDTO) {
        return createService.createContractEvent(contractEventDTO);
    }

    /**
     * Get a contract event by its ID.
     *
     * @param id The unique identifier of the contract event.
     * @return Mono of the requested ContractEventDTO.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a contract event by ID",
            description = "Fetches the contract event associated with the specified ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the contract event.",
                            content = @Content(schema = @Schema(implementation = ContractEventDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract event not found", content = @Content)
            }
    )
    public Mono<ContractEventDTO> getContractEventById(@PathVariable Long id) {
        return getService.getContractEvent(id);
    }

    /**
     * Retrieve all events related to a contract, with pagination support.
     *
     * @param contractId The ID of the contract whose events need to be retrieved.
     * @param paginationRequest Contains pagination information such as page size and number.
     * @return Mono containing paginated results of contract events.
     */
    @GetMapping("/contract/{contractId}")
    @Operation(
            summary = "Retrieve contract events by contract ID",
            description = "Fetches all contract events associated with a specific contract ID, with pagination support.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of contract events.",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No contract events found for the specified contract ID", content = @Content)
            }
    )
    public Mono<PaginationResponse<ContractEventDTO>> getContractEvents(
            @PathVariable Long contractId,
            PaginationRequest paginationRequest) {
        return getService.findByContractIdOrderByEventDateDesc(contractId, paginationRequest);
    }

    /**
     * Update an existing contract event by its ID.
     *
     * @param id The unique identifier of the contract event to be updated.
     * @param contractEventDTO The updated details of the contract event.
     * @return Mono of the updated ContractEventDTO.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract event",
            description = "Updates the details of an existing contract event identified by its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the contract event.",
                            content = @Content(schema = @Schema(implementation = ContractEventDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract event not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractEventDTO> updateContractEvent(@PathVariable Long id, @RequestBody ContractEventDTO contractEventDTO) {
        return updateService.updateContractEvent(id, contractEventDTO);
    }

    /**
     * Delete a contract event by its ID.
     *
     * @param id The unique identifier of the contract event to be deleted.
     * @return Mono signaling the deletion completion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract event",
            description = "Deletes the contract event associated with the specified ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the contract event.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contract event not found", content = @Content)
            }
    )
    public Mono<Void> deleteContractEvent(@PathVariable Long id) {
        return deleteService.deleteContractEvent(id);
    }
}