# Prerequisites
### Windows
java: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u222-b10/OpenJDK8U-jdk_x64_windows_hotspot_8u222b10.msi
\
maven: http://apache.uib.no/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.zip

### Linux
java: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u222-b10/OpenJDK8U-jdk_x64_linux_hotspot_8u222b10.tar.gz
\
maven: http://apache.uib.no/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz

# cse-config-cli

Command line interface for cse configuration files

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cse-config-cli
$ java -jar ./target/cse-config-cli-<version>-jar-with-dependencies.jar --config ./src/test/resources/validator/cse-config-valid.json
```

## clone -> build -> run (with debug output) -> generate configuration.owl
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cse-config-cli
$ java -Dosp.validator.log.level=debug -jar ./target/cse-config-cli-<version>-jar-with-dependencies.jar --config ./src/test/resources/validator/cse-config-valid.json -s ./
```

# cse-config-http

HTTP servlet interface for cse configuration validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cse-config-http
$ java -Dosp.validator.log.level=debug -jar ./target/cse-config-http-<version>-jar-with-dependencies.jar --port <your-favourite-port>
- open web browser
- go to localhost:<your-favourite-port>/validate?configuration=./src/test/resources/validator/cse-config-valid.json
```

# osp-model-description-cli

Command line interface for osp model description files

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd osp-model-description-cli
$ java -jar ./target/osp-model-description-cli-<version>-jar-with-dependencies.jar -osp-model-description ./src/test/resources/validator/cse-config-valid.json -fmu ./src/test/resources/CraneController.fmu 
```