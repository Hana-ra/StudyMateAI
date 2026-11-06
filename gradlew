#!/bin/sh

DIR=`dirname "$PRG"`
APP_HOME=`cd "${DIR}/" ; pwd`

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

exec java -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
