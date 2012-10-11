#!/bin/bash
mkdir -p assets/webroot
rm assets/webroot/*.jet
zip -r9 assets/webroot/webroot.jet webroot

# this is needed only if you will create a zip
# because android restricts some extensions and
# you will have to store zip as multipart
#pushd assets/webroot
#split webroot.zip -b 1048576 --additional-suffix=.zip -d webroot.
#popd
#rm assets/webroot/webroot.zip
