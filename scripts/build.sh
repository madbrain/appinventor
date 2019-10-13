#!/bin/sh

if [ -z "$AI_ROOT" ]; then
    echo "AI_ROOT must be defined"
    exit 1
fi

# copy stuffs from AI_ROOT
cp $AI_ROOT/build/blocklyeditor/blockly-all.js \
    ../ai-front/src/main/resources/com/google/appinventor/client/editor/youngandroid/blockly.js

cp $AI_ROOT/build/components/simple_components.* \
    ../ai-front/src/gen/java/com/google/appinventor

cp $AI_ROOT/build/components/ComponentTranslation/src/com/google/appinventor/client/*.java \
    ../ai-front/src/gen/java/com/google/appinventor/client

# Build Maven
(cd ..; mvn install)

# Build Containers
(cd app; sh build.sh)
(cd buildserver; sh build.sh)