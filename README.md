# Career Practice Starter

This repository is a practice environment for using tools that show up often in production work:

- GitHub repository structure
- CI with GitHub Actions
- Docker and Docker Compose
- Kubernetes manifests
- Spring Boot multi-service structure
- Basic automated tests
- Environment-variable based AI service wiring
- Image publishing to GHCR
- Kubernetes deployment automation from GitHub Actions

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

## CI/CD flow

There are now two GitHub Actions workflows:

- `ci`: runs on every push and pull request, then executes `test` and `package`
- `cd`: runs on `main` pushes or manual dispatch, builds Docker images, pushes them to GHCR, and deploys to Kubernetes when secrets are configured

### What `cd` does

1. Builds one Docker image per service
2. Pushes images to `ghcr.io`
3. Writes a kubeconfig from GitHub Secrets
4. Creates or updates the Kubernetes image pull secret
5. Renders a Kubernetes manifest with the current image tags
6. Applies the manifest and waits for rollout

### GitHub Secrets you need

- `KUBE_CONFIG`
  Add the full kubeconfig file content for the cluster you want Actions to deploy to.
- `GHCR_PULL_TOKEN`
  A GitHub personal access token with at least `read:packages` so the cluster can pull private GHCR images.
- `OPENAI_API_KEY`
  Optional. If present, the workflow stores it as a Kubernetes Secret for `ai-service`.

If `KUBE_CONFIG` or `GHCR_PULL_TOKEN` is missing, the `cd` workflow still publishes images but skips deployment.

### Kubernetes files

- Local practice with manually built images: [k8s/local.yaml](/Users/Home/spring/prac/k8s/local.yaml)
- GitHub Actions deployment template: [k8s/ghcr-template.yaml](/Users/Home/spring/prac/k8s/ghcr-template.yaml)

### GHCR image names

The workflow publishes these images:

- `ghcr.io/<github-owner>/career-practice-user-service`
- `ghcr.io/<github-owner>/career-practice-product-service`
- `ghcr.io/<github-owner>/career-practice-order-service`
- `ghcr.io/<github-owner>/career-practice-ai-service`

## Practice checklist

- Change a port or environment variable and rerun the service.
- Break a test and confirm CI fails.
- Build one image with Docker and inspect the container logs.
- Apply the Kubernetes manifest and compare it to docker-compose.
- Add one endpoint to one service and trace where tests and deployment config must change.
- Replace the AI stub with a real OpenAI API call later.
- Push to `main` and watch `cd` publish images to GHCR.
- Add `KUBE_CONFIG` and `GHCR_PULL_TOKEN`, then watch the rollout complete in Actions.
- Change one service and observe how only the image tag changes while the deployment flow stays the same.

## Useful commands

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
docker compose up --build
kubectl apply -f .\k8s\local.yaml
git status
```

## Notes for practice

This repository is intentionally not a full production platform. The point is to let you touch the files and commands that backend teams actually handle:

- service separation
- Docker image builds
- test automation
- image registry push
- Kubernetes manifest management
- secret injection
- rollout verification
