# Common Platform Contract Management Service

## Table of Contents
- Overview
- Features
- Architecture
  - Modules
  - Technology Stack
- Data Model
- API Documentation
- Setup and Installation
  - Prerequisites
  - Local Development Setup
  - Docker Deployment
- Configuration
- Development Guidelines
- Contributing

## Overview
The Common Platform Contract Management Service is a microservice that provides comprehensive contract management capabilities for the Firefly platform. It enables the creation, tracking, and management of contracts, including their terms, parties, documents, events, and risk assessments.

## Features
- Contract lifecycle management
- Contract terms and conditions tracking
- Contract party management
- Document management integration
- Contract event tracking
- Risk assessment and monitoring
- RESTful API with comprehensive documentation

## Architecture
This microservice follows a modular architecture with clear separation of concerns:

### Modules
- **common-platform-contract-mgmt-interfaces**: Contains DTOs and API interfaces
- **common-platform-contract-mgmt-models**: Contains database entities and repositories
- **common-platform-contract-mgmt-core**: Contains business logic and service implementations
- **common-platform-contract-mgmt-web**: Contains REST controllers and web configuration

### Technology Stack
- Java 21
- Spring Boot
- Spring WebFlux (Reactive programming)
- PostgreSQL
- Flyway for database migrations
- Maven for dependency management
- Docker for containerization
- OpenAPI/Swagger for API documentation

## Data Model
Below is the complete entity-relationship diagram, modeled in Mermaid and including attributes (with types) and relationships. Read-only fields are noted with [read-only].

```mermaid
classDiagram
  direction TB

  class Contract {
    +Long contractId [read-only]
    +String contractNumber
    +Long productCatalogId
    +Long productId
    +ContractStatusEnum contractStatus
    +LocalDateTime startDate
    +LocalDateTime endDate
    +String documentManagerRef
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractParty {
    +Long contractPartyId [read-only]
    +Long contractId
    +Long partyId
    +Long roleInContractId
    +LocalDateTime dateJoined
    +LocalDateTime dateLeft
    +Boolean active
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractDocument {
    +Long contractDocumentId [read-only]
    +Long contractId
    +String documentType
    +String documentManagerRef
    +LocalDateTime dateAdded
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractStatusHistory {
    +Long contractStatusHistoryId [read-only]
    +Long contractId
    +StatusCodeEnum statusCode
    +LocalDateTime statusStartDate
    +LocalDateTime statusEndDate
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractTerm {
    +Long contractTermId [read-only]
    +Long contractId
    +TermTypeEnum termType
    +String termDescription
    +BigDecimal numericValue
    +String valueUnit
    +LocalDateTime effectiveDate
    +LocalDateTime expirationDate
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractEvent {
    +Long contractEventId [read-only]
    +Long contractId
    +EventTypeEnum eventType
    +LocalDateTime eventDate
    +String eventDescription
    +String documentManagerRef
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractRiskAssessment {
    +Long contractRiskAssessmentId [read-only]
    +Long contractId
    +BigDecimal riskScore
    +RiskLevelEnum riskLevel
    +LocalDateTime assessmentDate
    +String assessor
    +String notes
    +LocalDateTime dateCreated [read-only]
    +LocalDateTime dateUpdated [read-only]
  }

  class ContractStatusEnum {
    <<enumeration>>
    DRAFT
    PENDING
    ACTIVE
    SUSPENDED
    CLOSED
    CANCELLED
  }

  class StatusCodeEnum {
    <<enumeration>>
    DRAFT
    ACTIVE
    DELINQUENT
    DEFAULT
    CLOSED
    CANCELLED
  }

  class TermTypeEnum {
    <<enumeration>>
    INTEREST_RATE
    CREDIT_LIMIT
    FEE_SCHEDULE
    PAYMENT_SCHEDULE
    PENALTY_RATE
  }

  class EventTypeEnum {
    <<enumeration>>
    RENEWAL
    AMENDMENT
    CANCELLATION
    SUSPENSION
    REACTIVATION
    TERMINATION
  }

  class RiskLevelEnum {
    <<enumeration>>
    LOW
    MEDIUM
    HIGH
    CRITICAL
  }

  Contract "1" --> "0..*" ContractParty : has
  Contract "1" --> "0..*" ContractDocument : has
  Contract "1" --> "0..*" ContractStatusHistory : has
  Contract "1" --> "0..*" ContractTerm : has
  Contract "1" --> "0..*" ContractEvent : has
  Contract "1" --> "0..*" ContractRiskAssessment : has
```

Validation and filtering notes:
- Dates are validated via @ValidDate
- Risk score validated via @ValidInterestRate
- Identifiers marked with @FilterableId are supported as query filters

Notable recent changes:
- Added productCatalogId to Contract and associated DB migrations.
- Updated ContractParty to use roleInContractId.
- Removed product_type enum references.

## API Documentation
The service provides a RESTful API with the following main endpoints:
- `/api/v1/contracts`: Contract management
- `/api/v1/contracts/{contractId}/parties`: Contract party management
- `/api/v1/contracts/{contractId}/documents`: Contract document management
- `/api/v1/contracts/{contractId}/terms`: Contract term management
- `/api/v1/contracts/{contractId}/events`: Contract event management
- `/api/v1/contracts/{contractId}/risk-assessments`: Contract risk assessment management

Detailed API documentation is available via Swagger UI at `/swagger-ui.html` when the service is running.

## Setup and Installation

### Prerequisites
- Java 21
- Maven 3.8+
- PostgreSQL 14+
- Docker (optional)

### Local Development Setup
1. Clone the repository
2. Configure database connection in `application.yml`
3. Run `mvn clean install` to build the project
4. Run `mvn spring-boot:run` to start the application

### Docker Deployment
The service can be deployed as a Docker container:

```bash
# Build the Docker image
docker build -t common-platform-contract-mgmt .

# Run the container
docker run -p 8080:8080 common-platform-contract-mgmt
```

## Configuration
The service can be configured through environment variables or application properties:

| Property | Description | Default |
|----------|-------------|---------|
| `spring.datasource.url` | Database URL | jdbc:postgresql://localhost:5432/contract_mgmt |
| `spring.datasource.username` | Database username | postgres |
| `spring.datasource.password` | Database password | postgres |
| `server.port` | Application port | 8080 |

## Development Guidelines
- Follow standard Java coding conventions
- Write unit tests for all new functionality
- Document all public APIs using OpenAPI annotations
- Use the provided DTOs for data transfer between layers
- Follow the reactive programming model with WebFlux

## Contributing
1. Create a feature branch from `develop`
2. Implement your changes
3. Write or update tests
4. Submit a pull request