# SkyTrail

A DAG orchestration system with workers, scheduler, and orchestrator built with Spring Boot.

## Overview

SkyTrail is a workflow orchestration platform that allows you to define, schedule, and execute Directed Acyclic Graphs (DAGs) of tasks. Each DAG can contain multiple tasks with dependencies, and the system manages their execution through dedicated workers controlled by an orchestrator and scheduler.

## Tech Stack

- **Java 21** with Eclipse Temurin
- **Spring Boot 3.5.5** (Web, JPA)
- **PostgreSQL 15** database
- **Flyway** database migrations
- **MapStruct** for DTO mapping
- **Docker & Docker Compose** for containerization

## Quick Start

### Prerequisites
- Docker and Docker Compose
- Java 21 (for local development)

### Running with Docker
```bash
# Start the application and database
docker-compose up --build

# The API will be available at http://localhost:8080
```

### Local Development
```bash
# Start only PostgreSQL
docker-compose up postgres -d

# Run the application locally
./gradlew bootRun
```

## API Endpoints

### DAGs
- `GET /api/v1/dags` - List all DAGs
- `POST /api/v1/dags/{dagId}/run` - Execute a DAG

## Database Schema

The system includes the following entities:
- **DAG** - Workflow definitions
- **DAG_RUN** - Execution instances of DAGs
- **TASK** - Individual work units within DAGs
- **TASK_RUN** - Execution instances of tasks
- **TASK_DEPENDENCY** - Dependencies between tasks
- **COMMAND** - Executable commands for tasks

## Project Structure

```
src/main/java/com/pilgrim/skytrail/
├── application/
│   └── services/          # Business logic layer
├── domain/
│   ├── dtos/             # Data Transfer Objects
│   ├── enums/            # Enumerations
│   ├── mappers/          # MapStruct mappers
│   └── model/            # JPA entities
└── infrastructure/
    ├── http/             # REST controllers
    └── repository/       # Data access layer
```

## Development

### Build
```bash
./gradlew build
```

### Run Tests
```bash
./gradlew test
```

### Database Migrations
Database schema is managed by Flyway migrations located in `src/main/resources/db/migration/`.

## Docker

The application uses a multi-stage Docker build:
- Build stage: Uses Gradle to compile and package the application
- Runtime stage: Uses Eclipse Temurin JRE for minimal footprint

## Configuration

The application can be configured through `application.properties`:
- Database connection settings
- Spring profiles
- Logging levels

For Docker deployment, the application connects to PostgreSQL using the service name `postgres:5432`.