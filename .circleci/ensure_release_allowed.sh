#!/bin/bash
set -e

if [[ "$CIRCLE_PROJECT_USERNAME" == "$CIRCLE_USERNAME" ]]; then
  exit 0
else
  echo "User $CIRCLE_USERNAME is not $CIRCLE_PROJECT_USERNAME; not allowed to trigger the release workflow!"
  exit 1
fi
