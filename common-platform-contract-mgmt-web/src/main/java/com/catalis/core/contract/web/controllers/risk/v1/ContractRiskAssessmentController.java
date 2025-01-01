package com.catalis.core.contract.web.controllers.risk.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.risk.v1.ContractRiskAssessmentCreateService;
import com.catalis.core.contract.core.services.risk.v1.ContractRiskAssessmentDeleteService;
import com.catalis.core.contract.core.services.risk.v1.ContractRiskAssessmentGetService;
import com.catalis.core.contract.core.services.risk.v1.ContractRiskAssessmentUpdateService;
import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
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
@RequestMapping("/api/v1/contracts-risk-assessments")
@Tag(name = "Contract Risk Assessment API", description = "API endpoints for creating, retrieving, updating, and deleting contract risk assessments, with support for pagination and filtering by contract.")
public class ContractRiskAssessmentController {

    @Autowired
    private ContractRiskAssessmentCreateService createService;

    @Autowired
    private ContractRiskAssessmentGetService getService;

    @Autowired
    private ContractRiskAssessmentUpdateService updateService;

    @Autowired
    private ContractRiskAssessmentDeleteService deleteService;

    /**
     * Create a new contract risk assessment.
     *
     * @param dto The DTO containing the details of the new risk assessment.
     * @return Mono of the created ContractRiskAssessmentDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new contract risk assessment",
            description = "Creates a new contract risk assessment using the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the contract risk assessment.",
                            content = @Content(schema = @Schema(implementation = ContractRiskAssessmentDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractRiskAssessmentDTO> createContractRiskAssessment(@RequestBody ContractRiskAssessmentDTO dto) {
        return createService.createContractRiskAssessment(dto);
    }

    /**
     * Retrieve a contract risk assessment by its ID.
     *
     * @param id The unique identifier of the assessment.
     * @return Mono of the requested ContractRiskAssessmentDTO.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve contract risk assessment by ID",
            description = "Fetches the contract risk assessment associated with the specified ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the contract risk assessment.",
                            content = @Content(schema = @Schema(implementation = ContractRiskAssessmentDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract risk assessment not found", content = @Content)
            }
    )
    public Mono<ContractRiskAssessmentDTO> getContractRiskAssessmentById(@PathVariable Long id) {
        return getService.getContractRiskAssessment(id);
    }

    /**
     * List all risk assessments for a contract with pagination.
     *
     * @param contractId The ID of the contract whose risk assessments are being fetched.
     * @param paginationRequest The pagination request containing page and size details.
     * @return Mono containing the paginated list of ContractRiskAssessmentDTOs.
     */
    @GetMapping("/contract/{contractId}")
    @Operation(
            summary = "Retrieve risk assessments for a specific contract",
            description = "Fetches all risk assessments related to a contract with pagination support.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the list of contract risk assessments.",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No risk assessments found for the specified contract", content = @Content)
            }
    )
    public Mono<PaginationResponse<ContractRiskAssessmentDTO>> getRiskAssessmentsForContract(
            @PathVariable Long contractId,
            PaginationRequest paginationRequest) {
        return getService.findByContractIdOrderByAssessmentDateDesc(contractId, paginationRequest);
    }

    /**
     * Update a contract risk assessment by its ID.
     *
     * @param id  The unique identifier of the contract risk assessment.
     * @param dto The updated risk assessment details.
     * @return Mono of the updated ContractRiskAssessmentDTO.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contract risk assessment",
            description = "Updates the details of the specified contract risk assessment.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the contract risk assessment.",
                            content = @Content(schema = @Schema(implementation = ContractRiskAssessmentDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Contract risk assessment not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
            }
    )
    public Mono<ContractRiskAssessmentDTO> updateContractRiskAssessment(
            @PathVariable Long id, @RequestBody ContractRiskAssessmentDTO dto) {
        return updateService.updateContractRiskAssessment(id, dto);
    }

    /**
     * Delete a contract risk assessment by its ID.
     *
     * @param id The unique identifier of the contract risk assessment to be deleted.
     * @return Mono signaling completion of the deletion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a contract risk assessment",
            description = "Deletes the specified contract risk assessment by its ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the contract risk assessment.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contract risk assessment not found", content = @Content)
            }
    )
    public Mono<Void> deleteContractRiskAssessment(@PathVariable Long id) {
        return deleteService.deleteContractRiskAssessment(id);
    }
}