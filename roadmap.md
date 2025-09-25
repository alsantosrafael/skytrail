# Skytrail Architecture Decision Records (ADRs) and Roadmap

This document consolidates the core architecture decisions and the incremental roadmap for **Skytrail**, an MVP distributed DAG orchestrator inspired by Airflow. It's designed to be simple, functional, and impactful for recruiters and managers.

---

# ADR-001: Architecture of Skytrail (MVP Distributed Scheduler / Orchestrator)

## Title
Skytrail Core architecture for MVP.

## Status
Accepted (baseline)

## Context
We are building **Skytrail**, a distributed DAG orchestrator inspired by Airflow. The project must:
- Showcase distributed system thinking (orchestration, messaging, idempotency).
- Provide a compact but strong portfolio artifact: easy to understand, impactful for interviews.
- Allow incremental evolution: MVP first, then advanced features like retries, caching, logs, observability.
- Use a **DDD light** approach to separate definitions (DAGs and tasks) from executions (DAGRuns and TaskRuns).

This means:
- **Dag/Task** = persistent workflow blueprint.
- **DagRun/TaskRun** = execution snapshots, fully loggable.
- **Command** = encapsulation of "what to execute" (script reference + hash/protocol).

## Decision
Architecture follows **Domain-Driven Design (DDD light)** principles:
- **Domain Layer**: core business entities (`Dag`, `Task`, `Command`, `DagRun`, `TaskRun`) and rules. Domain is pure, unaware of frameworks.
- **Application Layer**: use cases orchestrating domain logic (e.g., start a DAG → generates a DagRun + TaskRuns).
- **Infrastructure Layer**: all I/O to the outside world → persistence (JPA repositories), messaging (Kafka producers/consumers when added), scheduling, HTTP controllers.
- **Interfaces Layer**: thin controllers (reside technically in infra), translating REST to/from DTOs.

### Domain Features Planned
- Register DAGs (blueprints) via API.
- Version DAGs (eventually, with immutability).
- Trigger DAG execution on demand or via schedule.
- Track DAGRun/TaskRun lifecycle (WAITING, RUNNING, SUCCESS/FAILED).
- Support scripts via `Command` objects with validation/protocol integrity.

### External Touchpoints
- Postgres for persistence.
- REST API for all CRUD and orchestration commands.
- Future: Kafka for async communication.
- All boundary logic (HTTP APIs, DB access) lives under **infrastructure** adapters.

## Consequences
- Domain is clean and testable; no Spring annotations inside.
- Infra holds the messy details (controllers, scheduling, DB, messaging).
- Clear blueprint for recruiters: separation of concerns, not over-engineered.

---

# ADR-002: Deployment Approach — Monolith Core + Independent Worker

## Title
Deployment choice for MVP.

## Status
Accepted

## Context
Skytrail conceptually has three roles:
- **Maestro:** DAG orchestration, dependency resolution, state management.
- **Scheduler:** triggers DAGs at the right time.
- **Worker:** consumes tasks, executes commands, publishes results.

We need to decide on deployment boundaries for MVP.

## Decision
- **Core Monolith** (Spring Boot app) = Maestro + Scheduler + infra APIs + persistence.
- **Worker** = separate lightweight Spring Boot app (can be scaled horizontally).
- **Messaging integration** (Kafka for queue/result) comes later.

This delivers two deployables that demonstrate distributed design without overwhelming infrastructure.

## Consequences
- Simple enough to run locally via docker-compose.
- Already demonstrates distributed execution by scaling workers.
- Scheduler remains embedded in Core for now; separation can come later.

---

# ADR-003: Roadmap to Features, Deployment, CI/CD, and Observability

## Title
Skytrail product roadmap from MVP to production features.

## Status
Accepted (living document)

## Context
We want a clear incremental roadmap, showing **feature growth** (product functionality) and **tech expansion** (infra, CI/CD, monitoring). Recruiters and managers must see we know how to grow responsibly.

## Decision (Roadmap)
Incremental phases:

### Phase 0 — MVP
- Features: DAG CRUD, create DagRun/TaskRuns via API.
- Tech: Core monolith + Postgres + Flyway migrations. Worker stub integrated.
- Run: docker-compose.
- API Exposure: REST over HTTP (all inside infra layer).

### Phase 1 — Worker Separation
- Worker as standalone process.
- Core still manages orchestration. Worker can poll DB (simplest) to fetch tasks.
- Demonstrates proper “control-plane vs data-plane” separation.

### Phase 2 — Messaging (Kafka)
- Introduce Kafka in docker-compose.
- Core publishes tasks to `task.queue`.
- Workers subscribe, execute, respond on `task.result`.
- Core consumes `task.result` and updates DB.
- Adds resilience: retries, idempotent task_run updates.

### Phase 3 — CI/CD (GitHub Actions)
- Pull requests: build, test, validate migrations.
- Main branch merges: build/push Docker images to GitHub Container Registry.

### Phase 4 — Deployment
- Dev with docker-compose (Core + Workers + Postgres + Kafka).
- Production: K8s manifests or Helm (Core Deployment, Worker Deployment, Postgres external, Kafka cluster).

### Phase 5 — Observability
- Add Spring Boot Actuator end-points.
- Export metrics with Micrometer → Prometheus.
- Grafana dashboards (DAG success %, task latency, worker throughput).
- Log aggregation: container logs to ELK or Loki stack.

### Phase 6 — Inter-Task State Sharing
- Enable tasks to share state/data between executions (like Linux file descriptors).
- Design approaches: State Handles, State Registry, or Artifact Declaration patterns.
- Use cases: OAuth tokens, certificates, processed data files, API responses.
- Domain considerations: Maintain clean separation while enabling data flow.
- Storage options: S3 handles, Redis cache, or filesystem references.
- **Key challenge**: Pipe task outputs to next task without breaking domain rules.

### Phase 7 — Resilience Improvements
- Support retry policies (`max_retries`, `retry_count`).
- Dead Letter Queue for stuck tasks.
- Timeout monitoring.
- Distributed lock for Maestro scaling.

### Phase 8 — Extended Product Features
- Simple Web UI (view DAGs, inspect runs).
- RBAC for multi-user.
- DAG versioning & effective dating.
- Pluggable command types (SQL, Python, Bash, HTTP).

## Consequences
- Product roadmap is business-friendly and technical. MVP is demoable early.
- CI/CD and Observability show maturity.
- Incrementality keeps complexity manageable.

---

# ✅ Architecture Validation
- The architecture is **simple, functional, and incremental**.
- It enables a strong MVP demo locally (Core + Postgres).
- It provides a clear roadmap to scale out with Worker pools, messaging, and observability.
- Recruiters & hiring managers will see: clear domain modeling, DDD discipline, distributed mindset, CI/CD automation, observability hooks.
- **Impact:** a clean, compelling project to showcase distributed architecture thinking and practical implementation.

