# Description
A java command line interface tool for validating `OspSystemStructure.xml` and `OspModelDescription.xml` files.

# Usage
After building the project by following the build instructions [here](../README.md), `msmi-cli.jar` can be found in
`msmi-validator/osp-validator-cli/target` and used as follows:

```
$ java -jar msmi-cli.jar
Usage: msmi-cli.jar [options] [command] [command options]
  Options:
    --help, -h
      Print help
    --version, -v
      Print version
  Commands:
    osp-system-structure      Validate OspSystemStructure.xml
      Usage: osp-system-structure [options]
        Options:
          -file
            Path to OspSystemStructure.xml

    osp-model-description      Validate OspModelDescription.xml
      Usage: osp-model-description [options]
        Options:
          -file
            Path to OspModelDescription.xml
          -fmu
            Path to fmu
``` 

