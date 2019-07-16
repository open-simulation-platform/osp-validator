# msmi-validator-cli

Command line interface for msmi-validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cli
$ java -jar ./target/msmi-validator-cli-0.4.0-SNAPSHOT-jar-with-dependencies.jar --osp-ontology ../core/src/test/resources/validator/osp.owl --cse-config ../core/src/test/resources/validator/cse-config-valid.json
```

## clone -> build -> run (with debug output) -> generate configuration.owl
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ cd cli
$ java -Dmsmi.validator.log.level=debug -jar ./target/msmi-validator-cli-0.4.0-SNAPSHOT-jar-with-dependencies.jar --osp-ontology ../core/src/test/resources/validator/osp.owl --cse-config ../core/src/test/resources/validator/cse-config-valid.json -s ./
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
- go to localhost:<your-favourite-port>/validate?configuration=../core/src/test/resources/validator/cse-config-valid.json&ontology=../core/src/test/resources/validator/osp.owl
```
