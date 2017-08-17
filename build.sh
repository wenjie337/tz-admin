#!/bin/bash
#
#  Auto build shell script for cashbox
#  Create by Lytsing Huang 2014-12-13
#

if [ $# -lt 1 ]; then
  echo "Usage: $0 [p|t|d]";
  exit;
fi

CONFIG=''

case $1 in
  p | production)
    CONFIG="p"
    ;;
  pre | pre_production)
    CONFIG="pre"
    ;;
  t | test)
    CONFIG="t"
    ;;
  d | devleopment)
    CONFIG="d"
    ;;
  *)
    echo "Error! unknown parameter."
    exit 1
    ;;
esac

export GRADLE_OPTS="-Xmx512m -XX:MaxPermSize=128m"

gradle -x test --stacktrace --refresh-dependencies -Penv=$CONFIG build

