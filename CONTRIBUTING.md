Contributor guidelines
======================

This document contains a set of rules and guidelines for everyone who wishes
to contribute to the contents of this repository, hereafter referred to as
"the software".


General
-------
All contributors implicitly agree to license their contribution under the same
terms as the rest of the software.  See the `LICENSE` file for details.

All contributions to the software, in the form of changes, removals or
additions to source code and other files under source control, shall be made
via pull requests.  A pull request must always be reviewed and merged by someone
other than its author.


Workflow
--------
This repository uses the [gitflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) 
workflow. In short, it means the following:

**Branch types**
* `master`  - Only contains release commits.
* `develop` - All development happens on this branch.
* `feature` - Branches from `develop` and are merged back into `develop`.
* `release` - Branches from `develop`. Final touches before releasing. When ready to release, merge into `master` and 
back into `develop`.
* `hotfix` - Branches from `master`. When hotfix is ready, merge back into `master` for new hotfix release and into 
`develop`.


Creating new releases
---------------------
Create new releases by running the `release.sh` script. It will prompt you for the version number to release, and the 
bump version. Typically, the bump increases the `minor` version by 1 and adds `-SNAPSHOT` at the end. The script will 
then create a release branch with the given version number, and a new commit on the develop branch with the bump 
version. The script updates all version numbers in the `pom.xml` files. 

When ready to release:

 - Merge the release branch into the master branch _and_ the develop branch
 - Run `mvn clean package` on the master branch
 - Upload binaries to new github release with the same name as the version number
