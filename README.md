# Description
This is a java project consisting of a java library and a command line interface tool to validate FMUs and simulation
configurations against the [open simulation platform](https://www.opensimulationplatform.com) specification.

# Prerequisites
### Windows
java: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u222-b10/OpenJDK8U-jdk_x64_windows_hotspot_8u222b10.msi
\
maven: http://apache.uib.no/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.zip

### Linux
java: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u222-b10/OpenJDK8U-jdk_x64_linux_hotspot_8u222b10.tar.gz
\
maven: http://apache.uib.no/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz

# Modules
[osp-validator-api](/osp-validator-api/README.md)

[osp-validator-core](/osp-validator-core/README.md)

[osp-validator-cli](/osp-validator-cli/README.md)

[osp-model-description](/osp-model-description/README.md)

[osp-system-structure](/osp-system-structure/README.md)

[osp-validator-gen](/osp-validator-gen/README.md)

# Build
```
$ git clone git@github.com:open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
```

`<module-dir>/target` will contain the compiled artifacts.