#!/usr/bin/env bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk use java 23-graalce
rm -rf target
mvn -Pnative package
