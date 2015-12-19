#!/bin/sh

usage() {
	echo "build.sh < clean | jar>"
}

[[ $1 != "clean" ]] && [[ $1 != jar ]] && usage && exit 1

ant $1