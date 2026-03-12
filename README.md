# Career Practice Starter

This repository is a practice environment for using tools that show up often in production work:

- GitHub repository structure
- CI with GitHub Actions
- Docker and Docker Compose
- Kubernetes manifests
- Spring Boot multi-service structure
- Basic automated tests
- Environment-variable based AI service wiring

The code is intentionally simple. The goal is to practice setup, commands, and configuration without getting blocked by business logic.

## Project structure

```text
.
|-- .github/workflows/ci.yml
|-- docker-compose.yml
|-- k8s/local.yaml
|-- scripts/
|-- services/
|   |-- user-service
|   |-- product-service
|   |-- order-service
|   `-- ai-service
`-- pom.xml
```

## Prerequisites

- Java 17
- Docker Desktop
- Kubernetes enabled in Docker Desktop or another local cluster
- Git

## First run

1. Check local tools.

```powershell
.\scripts\check-env.ps1
```

2. Run tests.

```powershell
.\scripts\test.ps1
```

3. Start all services with Docker Compose.

```powershell
docker compose up --build
```

4. Verify endpoints.

```text
http://localhost:8081/api/users/ping
http://localhost:8082/api/products/ping
http://localhost:8083/api/orders/ping
http://localhost:8084/api/ai/config
http://localhost:8081/actuator/health
```

5. Apply Kubernetes manifests.

```powershell
kubectl apply -f .\k8s\local.yaml
kubectl get pods -n career-practice
```

## Practice checklist

- Change a port or environment variable and rerun the service.
- Break a test and confirm CI fails.
- Build one image with Docker and inspect the container logs.
- Apply the Kubernetes manifest and compare it to docker-compose.
- Add one endpoint to one service and trace where tests and deployment config must change.
- Replace the AI stub with a real OpenAI API call later.

## Useful commands

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
docker compose up --build
kubectl apply -f .\k8s\local.yaml
git status
```

