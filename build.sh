#!/bin/bash

# step1: update your project:
android update project -p . -n antik -t 1 --subprojects

# step2: build it and install it
ant debug
ant installd
