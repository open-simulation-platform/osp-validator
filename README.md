# msmi-validator
Ontology-based systems structure validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ java -jar ./target/msmi-validator-<version>-jar-with-dependencies.jar --osp-ontology ./src/test/resources/validator/osp.owl --cse-config ./src/test/resources/validator/cse-config.json
```

## clone -> build -> run (with debug output) -> generate configuration.owl
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ java -Dmsmi.validator.log.level=debug -jar ./target/msmi-validator-<version>-jar-with-dependencies.jar --osp-ontology ./src/test/resources/validator/osp.owl --cse-config ./src/test/resources/validator/cse-config.json -s ./
```
