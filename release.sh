#!/bin/bash

regex_release='^[0-9]+[.][0-9]+[.][0-9]+$'
regex_snapshot='^[0-9]+[.][0-9]+[.][0-9]+[-]SNAPSHOT$'

while ! [[ "$msmi_validator_version" =~ $regex_release ]]; do
  echo -n "Enter msmi-validator version to release on the format: 'MAJOR.MINOR.BUILD': "
  read -r msmi_validator_version
done

while ! [[ "$msmi_validator_bump_version" =~ $regex_snapshot ]]; do
  echo -n "Enter msmi-validator bump version on the format: 'MAJOR.MINOR.BUILD-SNAPSHOT': "
  read -r msmi_validator_bump_version
done

echo -n "Creating msmi-validator release: $msmi_validator_version. Bumping msmi-validator version to: $msmi_validator_bump_version. Press Enter to continue, CTRL-C to abort: "
read -r dummy

git checkout develop
git pull

mvn versions:set -DgenerateBackupPoms=false -DnewVersion="$msmi_validator_version" versions:commit
mvn dependency:tree -D verbose | grep conflict

git commit -am "New release: $msmi_validator_version"
git branch release/"$msmi_validator_version"

mvn versions:set -DgenerateBackupPoms=false -DnewVersion="$msmi_validator_bump_version" versions:commit
mvn dependency:tree -D verbose | grep conflict

git commit -am "Bumped to next SNAPTHOT version"
git push --set-upstream origin develop

git checkout release/"$msmi_validator_version"
git push --set-upstream origin release/"$msmi_validator_version"

echo -n "Done!"
