#!/bin/sh

cp -r ${AI_ROOT}/buildserver/build/run/lib lib

docker build -t ai-buildserver:1.0 .