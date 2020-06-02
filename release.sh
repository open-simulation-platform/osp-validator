#!/bin/bash

regex_release='^[0-9]+[.][0-9]+[.][0-9]+$'
regex_snapshot='^[0-9]+[.][0-9]+[.][0-9]+[-]SNAPSHOT$'

while ! [[ "$osp_validator_version" =~ $regex_release ]]; do
  echo -n "Enter osp-validator version to release on the format: 'MAJOR.MINOR.BUILD': "
  read -r osp_validator_version
done

while ! [[ "$osp_validator_bump_version" =~ $regex_snapshot ]]; do
  echo -n "Enter osp-validator bump version on the format: 'MAJOR.MINOR.BUILD-SNAPSHOT': "
  read -r osp_validator_bump_version
done

echo -n "Creating osp-validator release: $osp_validator_version. Bumping osp-validator version to: $osp_validator_bump_version. Press Enter to continue, CTRL-C to abort: "
read -r dummy

git checkout develop
git pull

mvn versions:set -DgenerateBackupPoms=false -DnewVersion="$osp_validator_version" versions:commit
mvn dependency:tree -D verbose | grep conflict

git commit -am "New release: $osp_validator_version"
git branch release/"$osp_validator_version"

mvn versions:set -DgenerateBackupPoms=false -DnewVersion="$osp_validator_bump_version" versions:commit
mvn dependency:tree -D verbose | grep conflict

git commit -am "Bumped to next SNAPTHOT version"
git push --set-upstream origin develop

git checkout release/"$osp_validator_version"
git push --set-upstream origin release/"$osp_validator_version"

echo -n "Done!"
