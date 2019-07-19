# Prerequisites
### Windows
java: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u222-b10/OpenJDK8U-jdk_x64_windows_hotspot_8u222b10.msi
\
maven: http://apache.uib.no/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.zip

### Linux
java: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u222-b10/OpenJDK8U-jdk_x64_linux_hotspot_8u222b10.tar.gz
\
maven: http://apache.uib.no/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz

# msmi-validator-cli

Command line interface for msmi-validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cli
$ java -jar ./target/msmi-validator-cli-0.4.0-SNAPSHOT-jar-with-dependencies.jar --ontology ../core/src/main/resources/osp.owl --config ../core/src/test/resources/validator/cse-config-valid.json
```

## clone -> build -> run (with debug output) -> generate configuration.owl
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cli
$ java -Dmsmi.validator.log.level=debug -jar ./target/msmi-validator-cli-0.4.0-SNAPSHOT-jar-with-dependencies.jar --ontology ../core/src/main/resources/osp.owl --config ../core/src/test/resources/validator/cse-config-valid.json -s ./
```

# msmi-validator-http

HTTP servlet interface for msmi-validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd http
$ java -Dmsmi.validator.log.level=debug -jar ./target/msmi-validator-http-<version>-jar-with-dependencies.jar --port <your-favourite-port>
- open web browser
- go to localhost:<your-favourite-port>/validate?configuration=../core/src/test/resources/validator/cse-config-valid.json&ontology=../core/src/main/resources/osp.owl
```
