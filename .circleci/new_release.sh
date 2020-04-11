#!/bin/bash
set -e
usage() {
  echo "Usage: $0 -v <version> [-r <revision=HEAD>]" >&2
  exit 1
}

VERSION=""
REVISION="HEAD"

while getopts ":v:r" opt; do
  case $opt in
  v)
    VERSION=$OPTARG
    ;;
  r)
    REVISION=$OPTARG
    ;;
  \?)
    echo "Invalid option: -$OPTARG" >&2
    exit 1
    ;;
  *)
    usage
    ;;
  esac
done

if [ -z "$VERSION" ]; then
  usage
fi

git tag ${VERSION} ${REVISION} -m "${VERSION}" &&
  git push --tags &&
  echo "New release ${VERSION} for revision $(git rev-parse --short "${REVISION}") requested successfully"
