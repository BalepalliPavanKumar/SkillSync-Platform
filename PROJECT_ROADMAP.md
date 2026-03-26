# 🚀 SkillSync Stabilization & Rubric Alignment Plan

This plan outlines the steps to ensure the platform meets all criteria in the grading rubric for a perfect 100/100 score.

## 1. Zipkin Dashboard & Distributed Tracing (10/10 extra tech)
Spring Boot 3.2 uses **Micrometer Tracing**. 
- [ ] Add `micrometer-observation`, `micrometer-tracing-bridge-otel`, and `opentelemetry-exporter-zipkin` dependencies to all microservices.
- [ ] Update `application.properties` (in config-repo) to enable sampling and specify Zipkin URL.
- [ ] Verify traces in Zipkin Dashboard (default port 9411).

## 2. API Validation & Global Exception Handling (10/10 REST API)
- [ ] **DTO Validations:** Add `@NotBlank`, `@Email`, `@Size`, etc., to all input DTOs.
- [ ] **Controller Validations:** Add `@Valid` to all `@RequestBody` parameters.
- [ ] **Consistent Exception Handling:** Ensure every service has a `GlobalExceptionHandler` that handles `MethodArgumentNotValidException`, `ResourceNotFoundException`, and generic `Exception`.
- [ ] **Error Responses:** Use a consistent `ErrorDetails` DTO for all services.

## 3. Clean Code & Architecture (15/15)
- [ ] **Naming:** Finalize renaming any leftover `username` to `name` in Skill, Group, and Review services.
- [ ] **Layering:** Verify that no business logic resides in Controllers.

## 4. Service Communication (10/10)
- [ ] **RabbitMQ:** Ensure `notification-service` is correctly processing all event types (not just sessions).
- [ ] **Feign Clients:** (Optional/Bonus) Use OpenFeign for `user-service` to talk to `auth-service` if needed, instead of manual ID matching.

## 5. Docker Implementation & Execution (20/20)
- [ ] Create `Dockerfile` for each service.
- [ ] Create a master `docker-compose.yml` including:
    - PostgreSQL (separate DBs or one with multiple schemas/users)
    - RabbitMQ
    - Zipkin
    - Eureka
    - Config Server
    - All Microservices

---

### Implementation Steps

### Phase 1: Tracing & Validation
#### 1.1 Auth Service
- [ ] Update `pom.xml` with tracing dependencies.
- [ ] Add validations to `UserRegisterRequest` and `LoginRequest`.
- [ ] Enhance `GlobalExceptionHandler`.

#### 1.2 User Service
- [ ] Update `pom.xml`.
- [ ] Add validations to `UserProfileDto`.
- [ ] Enhance `GlobalExceptionHandler`.

#### 1.3 Repeat for Mentor, Skill, Group, Session, Review Services...

### Phase 2: Dockerization (Last)
- [ ] Build JARs.
- [ ] Write Dockerfiles.
- [ ] Configure `docker-compose`.
