#!/usr/bin/env bash

set -euo pipefail

if [ "$#" -ne 2 ]; then
  echo "usage: render-k8s.sh <template> <output>" >&2
  exit 1
fi

template_file="$1"
output_file="$2"

required_vars=(
  USER_SERVICE_IMAGE
  PRODUCT_SERVICE_IMAGE
  ORDER_SERVICE_IMAGE
  AI_SERVICE_IMAGE
)

for var_name in "${required_vars[@]}"; do
  if [ -z "${!var_name:-}" ]; then
    echo "$var_name is required" >&2
    exit 1
  fi
done

sed \
  -e "s|__USER_SERVICE_IMAGE__|$USER_SERVICE_IMAGE|g" \
  -e "s|__PRODUCT_SERVICE_IMAGE__|$PRODUCT_SERVICE_IMAGE|g" \
  -e "s|__ORDER_SERVICE_IMAGE__|$ORDER_SERVICE_IMAGE|g" \
  -e "s|__AI_SERVICE_IMAGE__|$AI_SERVICE_IMAGE|g" \
  "$template_file" > "$output_file"

