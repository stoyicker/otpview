#!/bin/bash
set -e

uploadReleaseToGitHub() {
  git fetch --tags
  THIS_TAG=$(git describe --tags --abbrev=0)

  BODY="{
        \"tag_name\": \"$THIS_TAG\",
        \"target_commitish\": \"master\",
        \"name\": \"$THIS_TAG\",
        \"body\": \" \"
    }"

  # Create the release in GitHub and extract its id from the response
  RESPONSE_BODY=$(curl -s \
    -u "${CIRCLE_USERNAME}":"${GITHUB_TOKEN}" \
    --header "Accept: application/vnd.github.v3+json" \
    --header "Content-Type: application/json; charset=utf-8" \
    --request POST \
    --data "${BODY}" \
    https://api.github.com/repos/"${CIRCLE_PROJECT_USERNAME}"/"${CIRCLE_PROJECT_REPONAME}"/releases)

  # Extract the upload_url value
  UPLOAD_URL=$(echo ${RESPONSE_BODY} | jq -r .upload_url)

  cp library/build/outputs/aar/library-release.aar library.aar

  # Attach library
  LIBRARY_UPLOAD_URL=$(echo ${UPLOAD_URL} | sed "s/{?name,label}/?name=library-${THIS_TAG}.aar/")
  curl -s \
    -u "${CIRCLE_USERNAME}":"${GITHUB_TOKEN}" \
    --header "Accept: application/vnd.github.v3+json" \
    --header "Content-Type: application/zip" \
    --data-binary "@library.aar" \
    --request POST \
    "${LIBRARY_UPLOAD_URL}"

  cp demo/build/outputs/apk/release/demo-release.apk demo.apk

  # Attach demo
  DEMO_UPLOAD_URL=$(echo ${UPLOAD_URL} | sed "s/{?name,label}/?name=demo-${THIS_TAG}.apk/")
  curl -s \
    -u "${CIRCLE_USERNAME}":"${GITHUB_TOKEN}" \
    --header "Accept: application/vnd.github.v3+json" \
    --header "Content-Type: application/zip" \
    --data-binary "@demo.apk" \
    --request POST \
    "${DEMO_UPLOAD_URL}"

  echo "GitHub release complete."
}

uploadReleaseToGitHub
