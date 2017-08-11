#!/bin/bash

#
# Compare the Java code formatter profile in the setup file to an exported profile.
#

exported="${1:?Usage: $0 <export.xml>}"

diff --report-identical-files <(
sed -En '/jdt.ui.formatterprofiles/,/<\/value>/ { /<value>/,$ s/^ *//p
}' < eclipse-for-6031.setup | sed -e '1d;$d' -e 's/&lt;/</g' -e 's/&quot;/"/g'
) "$exported"
