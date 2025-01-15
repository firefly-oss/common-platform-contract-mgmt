package com.catalis.core.contract.web.controllers.events.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.events.v1.ContractEventServiceImpl;
import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Contract Events", description = "APIs for managing contract events")
@RestController
@RequestMapping("/api/v1/contracts/{contractId}/events")
public class ContractEventController {

    @Autowired
    private ContractEventServiceImpl service;

    @Operation(
            summary = "Retrieve Contract Events",
            description = "Retrieves a paginated list of events for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contract events",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No events found", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractEventDTO>>> getAllEvents(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.getAllEvents(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract Event",
            description = "Create a new event record for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractEventDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid event data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractEventDTO>> createEvent(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Event data to be created", required = true,
                    schema = @Schema(implementation = ContractEventDTO.class))
            @RequestBody ContractEventDTO dto
    ) {
        return service.createEvent(contractId, dto)
                .map(createdEvent -> ResponseEntity.status(201).body(createdEvent))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Contract Event",
            description = "Retrieve a specific event for the specified contract by event ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the event",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractEventDTO.class))),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @GetMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractEventDTO>> getEvent(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the event", required = true)
            @PathVariable Long eventId
    ) {
        return service.getEvent(contractId, eventId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract Event",
            description = "Update an existing event by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractEventDTO.class))),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @PutMapping(value = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractEventDTO>> updateEvent(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the event to update", required = true)
            @PathVariable Long eventId,

            @Parameter(description = "Updated event data", required = true,
                    schema = @Schema(implementation = ContractEventDTO.class))
            @RequestBody ContractEventDTO dto
    ) {
        return service.updateEvent(contractId, eventId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract Event",
            description = "Remove an existing event record from the contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
    })
    @DeleteMapping("/{eventId}")
    public Mono<ResponseEntity<Void>> deleteEvent(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the event to delete", required = true)
            @PathVariable Long eventId
    ) {
        return service.deleteEvent(contractId, eventId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}