$ErrorActionPreference = "Stop"

$commands = @(
    "java",
    "docker",
    "kubectl",
    "git"
)

foreach ($command in $commands) {
    if (-not (Get-Command $command -ErrorAction SilentlyContinue)) {
        Write-Error "$command is not installed or not on PATH."
    }
}

Write-Host "Tool check passed."
java -version
docker --version
kubectl version --client
git --version

