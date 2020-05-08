# Description
A java command line interface tool for validating `OspSystemStructure.xml` files.

This tool will also support validation of `OspModelDescription.xml` files eventually.

# Usage
After building the project by following the build instructions [here](../README.md), `msmi-cli.jar` can be found in
`msmi-validator/osp-validator-cli/target` and used as follows:

```
$ java -jar msmi-cli.jar
usage: java -jar msmi-cli.jar
 -osp_system_structure,--osp_system_structure <osp_system_structure>   Path to OspSystemStructure.xml
 -v,--version                                                          cli version
``` 

