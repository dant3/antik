#!/bin/bash

# step1: update your project:
android update project -p . -n antik -t 1 --subprojects

# step2: build it and install it
ant debug

if [ $? == 0 ]
then
  ant installd
else
  echo "You had some compilation problems. Please fix them and try again."
fi
