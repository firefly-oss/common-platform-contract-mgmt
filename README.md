# Common Platform Contract Management Service

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
The service manages the following key entities:
- **Contract**: Core entity representing a legal agreement
- **Contract Party**: Entities involved in the contract
- **Contract Document**: Documents associated with the contract
- **Contract Status History**: Historical record of contract status changes
- **Contract Term**: Terms and conditions of the contract
- **Contract Event**: Events related to the contract lifecycle
- **Contract Risk Assessment**: Risk evaluations for the contract

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